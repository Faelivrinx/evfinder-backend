package com.mypieceofcode.evfinder.utils;

import com.mypieceofcode.evfinder.command.TaskResponse;
import org.springframework.stereotype.Service;

public interface TaskFactory {

    TaskResponse createTaskResponse(boolean isSuccess, String taskName);

}
