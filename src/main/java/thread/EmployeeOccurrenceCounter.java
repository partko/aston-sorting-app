package thread;

import collection.CustomList;
import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeOccurrenceCounter {

    public static int countOccurrences(
            CustomList<Employee> employees,
            Employee target,
            int threadCount
    ) {
        if (employees == null || target == null) {
            throw new IllegalArgumentException("Employees and target must not be null");
        }

        if (threadCount <= 0) {
            throw new IllegalArgumentException("Thread count must be positive");
        }

        int size = employees.size();
        if (size == 0) return 0;

        threadCount = Math.min(threadCount, size);

        AtomicInteger result = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();

        int chunkSize = size / threadCount;
        int remainder = size % threadCount;

        int start = 0;

        for (int i = 0; i < threadCount; i++) {
            int end = start + chunkSize + (i < remainder ? 1 : 0);
            int from = start;

            Thread thread = new Thread(() -> {
                int localCount = 0;
                for (int j = from; j < end; j++) {
                    if (target.equals(employees.get(j))) {
                        localCount++;
                    }
                }
                result.addAndGet(localCount);
            });

            threads.add(thread);
            thread.start();
            start = end;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }

        return result.get();
    }
}
