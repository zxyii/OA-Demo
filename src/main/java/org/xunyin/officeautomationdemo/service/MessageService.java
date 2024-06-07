package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.Message;

import java.util.List;

public interface MessageService {
    void add(Message message);

    List<Message> list();
}
