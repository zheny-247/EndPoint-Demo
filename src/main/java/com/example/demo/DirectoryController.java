package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    private final DirectoryManager directoryManager = new DirectoryManager();

    @PostMapping("/{action}")
    public ResponseEntity<String> processCommand(@PathVariable String action, @RequestBody CommandArgs commandArgs) {
        String result;
        switch (action.toLowerCase()) {
            case "create":
                result = directoryManager.create(commandArgs.getArgs());
                break;
            case "move":
                result = directoryManager.move(commandArgs.getArgs());
                break;
            case "delete":
                result = directoryManager.delete(commandArgs.getArgs());
                break;
            case "list":
                result = directoryManager.list();
                break;
            default:
                result = "Error: Invalid command. Available commands: CREATE, MOVE, DELETE, LIST.";
        }

        return ResponseEntity.ok(result);
    }

}