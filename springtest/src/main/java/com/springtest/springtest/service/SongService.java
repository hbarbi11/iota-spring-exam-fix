package com.springtest.springtest.service;

import com.springtest.springtest.entity.Album;
import com.springtest.springtest.entity.Artist;
import com.springtest.springtest.entity.Song;
import com.springtest.springtest.exception.ValidationException;
import com.springtest.springtest.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song createSong (Song song) throws ValidationException {

        if(song.getTitle() == null || song.getTitle() == "") {
            throw new ValidationException("Cannot create Song without title", HttpStatus.BAD_REQUEST);
        }
        if(song.getLength() < 1 ) {
            throw new ValidationException("Invalid length", HttpStatus.BAD_REQUEST);
        }
        if(song.getArtist() == null || song.getArtist().getId() == null) {
            throw new ValidationException("Cannot create without Artist/artistId", HttpStatus.BAD_REQUEST);
        }
        if(song.getAlbum() == null || song.getAlbum().getId() == null) {
            throw new ValidationException("Cannot create without Album/albumId", HttpStatus.BAD_REQUEST);
        }

        return songRepository.save(song);
    }

    public List<Song> listSongs() {
        List<Song> songList = songRepository.findAll();
        return songList;
    }

    public Song getSongById(String id) throws ValidationException {
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isEmpty()) {
            throw new ValidationException("Invalid id", HttpStatus.BAD_REQUEST);
        }

        Song song = optionalSong.get();
        return song;
    }

    public Song songUpdate(String id, Song song) throws ValidationException {
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isEmpty()){
            throw new ValidationException("Cannot update Album", HttpStatus.BAD_REQUEST);
        }

        Song song1 = optionalSong.get();
        song1.setTitle(song1.getTitle());
        song1.setLength(song1.getLength());
        song1.setLyrics(song1.getLyrics());
        song1.setYear(song1.getYear());
        song1.setWriterName(song1.getWriterName());
        song1.setGenre(song1.getGenre());
        song1.setArtist(song1.getArtist());
        song1.setAlbum(song1.getAlbum());
        Song updatedSong = songRepository.save(song1);
        return updatedSong;
    }
}
