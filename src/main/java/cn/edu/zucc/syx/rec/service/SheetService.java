package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.UserSheets;

import java.util.List;


import java.util.List;

public interface SheetService {
    Sheet create(String sheetName, String description, String userId);
    Sheet delete(String host, String sheetId);
    List<Sheet> findByName(String name);
    Boolean open(String sheetId);
    Boolean close(String sheetId);
    List<UserSheets> listAll(String host);
    Sheet SheetInfo(String sheetId);
    Boolean addSong2Sheet(String sheetId, String songId);
    Boolean deleteSongFromSheet(String sheetId, String songId);
    public Sheet collect(String host, String sheedId);

}
