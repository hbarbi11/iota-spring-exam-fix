package com.springtest.springtest.controller;

import com.springtest.springtest.entity.Album;
import com.springtest.springtest.exception.ValidationException;
import com.springtest.springtest.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album) throws ValidationException {

        Album albumResult = albumService.createAlbum(album);

        return albumResult;
    }

    @PutMapping("/updatealbum/{id}")
    public Album updateAlbum(@PathVariable("id") String id, @RequestBody Album album) throws ValidationException {

            Album updatedAlbum = albumService.albumUpdate(id, album);
            return updatedAlbum;
    }

    @GetMapping("/albums")
    public List<Album> findAllAlbums() {

        return albumService.listAlbums();
    }

    @GetMapping("/findalbumsbyid/{id}")
    public Album findAlbumById(@PathVariable(name = "id") String id) throws Exception {
        Optional<Album> album = Optional.ofNullable(albumService.getAlbumById(id));
        if (album.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No superhero this id");
        }
        return album.get();
    }
}
