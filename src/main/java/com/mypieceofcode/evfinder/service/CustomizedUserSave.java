package com.mypieceofcode.evfinder.service;

import com.mypieceofcode.evfinder.command.ApiKey;
import com.mypieceofcode.evfinder.command.UserCommand;

public interface CustomizedUserSave {

    ApiKey createUser(UserCommand userCommand);
}
