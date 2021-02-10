package com.galvanize.playlist.Repository;

import com.galvanize.playlist.Model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
