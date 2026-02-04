package app.ui;

import app.commands.Command;

public record MenuItem(String key, String title, Command command) {
}
