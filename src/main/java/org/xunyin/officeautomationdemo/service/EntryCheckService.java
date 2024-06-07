package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.EntryCheck;

public interface EntryCheckService {
    EntryCheck check(int entryId);
    void updateResult(int id,int status,int result, String description);

    EntryCheck listInfo(int id);

    void initialize(int entryId, String checkPerson);
}
