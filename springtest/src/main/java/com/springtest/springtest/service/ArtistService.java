package com.springtest.springtest.service;

import com.springtest.springtest.entity.Artist;
import com.springtest.springtest.exception.ValidationException;
import com.springtest.springtest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {


    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist (Artist artist) throws ValidationException {

        if(artist.getFirstname() == null || artist.getFirstname() == "") {
            throw new ValidationException("Cannot create Artist without firstname");
        }
        if(artist.getLastname() == null || artist.getLastname() == "") {
            throw new ValidationException("Cannot create Artist without lastname");
        }
        return artistRepository.save(artist);

    }

    public List<Artist> listArtists() {
        List<Artist> artistList = artistRepository.findAll();
        return artistList;
    }

    public Artist getArtistById(String id) throws ValidationException {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if(optionalArtist.isEmpty()) {
            throw new ValidationException("Invalid id", HttpStatus.BAD_REQUEST);
        }

        Artist artist = optionalArtist.get();
        return artist;
    }

    public Artist artistUpdate(String id, Artist artist) throws ValidationException {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if(optionalArtist.isEmpty()){
            throw new ValidationException("Cannot update Artist", HttpStatus.BAD_REQUEST);
        }

        Artist artist1 = optionalArtist.get();
        artist1.setFirstname(artist1.getFirstname());
        artist1.setLastname(artist1.getLastname());
        Artist updatedArtist = artistRepository.save(artist1);
        return updatedArtist;
    }
}
