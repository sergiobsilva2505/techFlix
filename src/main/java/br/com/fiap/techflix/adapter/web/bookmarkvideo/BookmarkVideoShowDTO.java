package br.com.fiap.techflix.adapter.web.bookmarkvideo;

import br.com.fiap.techflix.adapter.web.user.UserViewDTO;
import br.com.fiap.techflix.adapter.web.video.VideoShowDTO;

public record BookmarkVideoShowDTO(String id, UserViewDTO user, VideoShowDTO video) {
}
