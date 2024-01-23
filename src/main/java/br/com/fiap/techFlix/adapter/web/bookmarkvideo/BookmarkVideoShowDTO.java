package br.com.fiap.techFlix.adapter.web.bookmarkvideo;

import br.com.fiap.techFlix.adapter.web.user.UserViewDTO;
import br.com.fiap.techFlix.adapter.web.video.VideoShowDTO;

public record BookmarkVideoShowDTO(String id, UserViewDTO user, VideoShowDTO video) {
}
