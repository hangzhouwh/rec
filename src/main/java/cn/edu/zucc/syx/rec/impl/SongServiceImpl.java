//package cn.edu.zucc.syx.rec.impl;
//
//import cn.edu.zucc.syx.rec.entity.Sheet;
//import cn.edu.zucc.syx.rec.entity.Song;
//import cn.edu.zucc.syx.rec.respository.SheetRepository;
//import cn.edu.zucc.syx.rec.respository.SongRepository;
//import cn.edu.zucc.syx.rec.service.SheetService;
//import cn.edu.zucc.syx.rec.service.SongService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SongServiceImpl implements SongService {
//
//    private final SongRepository songRepository;
//
//    @Autowired
//    public SongServiceImpl(SongRepository songRepository) {
//        this.songRepository = songRepository;
//    }
////
////    @Override
////    public Song searchSong(String song_id) {
////        return  songRepository.queryBySong_id(song_id);
////    }
//}