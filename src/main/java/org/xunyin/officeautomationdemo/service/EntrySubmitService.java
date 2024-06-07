package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.EntrySubmit;
import org.xunyin.officeautomationdemo.util.Result;

import java.util.List;

public interface EntrySubmitService {

    Result add(EntrySubmit entrySubmit);

    List<EntrySubmit> list();

    EntrySubmit listInfo1(int entryId);

    EntrySubmit listInfo2(int userId);


}

