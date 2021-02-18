package com.springtest.springtest;

import com.springtest.springtest.repository.AlbumRepository;
import com.springtest.springtest.service.AlbumService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {

    private static final String ID = "25145L";
    private static final String TITLE = "Something in the album";
    private static final String COUNT = "5151";

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService service;

}
