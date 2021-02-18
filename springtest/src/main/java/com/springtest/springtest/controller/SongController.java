package com.springtest.springtest.controller;

import com.springtest.springtest.entity.Artist;
import com.springtest.springtest.entity.Song;
import com.springtest.springtest.exception.ValidationException;
import com.springtest.springtest.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@RequestBody Song song) throws ValidationException {

        Song songResult = songService.createSong(song);

        return songResult;
    }

    @PutMapping("/updatesong/{id}")
    public Song updateSong(@PathVariable("id") String id, @RequestBody Song song) throws ValidationException {

        Song updatedSong = songService.songUpdate(id, song);
        return updatedSong;
    }

    @GetMapping("/artists")
    public List<Song> findAllSongs() {

        return songService.listSongs();
    }

    @GetMapping("/findartistsbyid/{id}")
    public Song findSongById(@PathVariable(name = "id") String id) throws Exception {
        Optional<Song> song = Optional.ofNullable(songService.getSongById(id));
        if (song.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No superhero this id");
        }
        return song.get();
    }
}