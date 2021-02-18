package com.springtest.springtest.controller;

import com.springtest.springtest.entity.Album;
import com.springtest.springtest.entity.Artist;
import com.springtest.springtest.exception.ValidationException;
import com.springtest.springtest.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) throws ValidationException {

        Artist artistResult = artistService.createArtist(artist);

        return artistResult;
    }

    @PutMapping("/updateartist/{id}")
    public Artist updateArtist(@PathVariable("id") String id, @RequestBody Artist artist) throws ValidationException {

        Artist updatedArtist = artistService.artistUpdate(id, artist);
        return updatedArtist;
    }

    @GetMapping("/artists")
    public List<Artist> findAllArtists() {

        return artistService.listArtists();
    }

    @GetMapping("/findartistsbyid/{id}")
    public Artist findArtistById(@PathVariable(name = "id") String id) throws Exception {
        Optional<Artist> artist = Optional.ofNullable(artistService.getArtistById(id));
        if (artist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No superhero this id");
        }
        return artist.get();
    }
}