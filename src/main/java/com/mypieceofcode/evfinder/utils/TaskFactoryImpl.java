package com.mypieceofcode.evfinder.utils;

import com.mypieceofcode.evfinder.command.TaskResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskFactoryImpl implements TaskFactory {

    @Override
    public TaskResponse createTaskResponse(boolean isSuccess, String taskName) {
        if (isSuccess) {
            return createSuccessEvent(taskName);
        } else {
            return createFailedEvent(taskName);
        }
    }

    private TaskResponse createSuccessEvent(String taskName) {
        return new TaskResponse(taskName, "success");
    }

    private TaskResponse createFailedEvent(String taskName) {
        return new TaskResponse(taskName, "failed");
    }
}
