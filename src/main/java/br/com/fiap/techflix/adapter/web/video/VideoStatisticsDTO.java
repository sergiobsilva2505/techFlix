package br.com.fiap.techflix.adapter.web.video;

import br.com.fiap.techflix.application.ports.VideoStatisticsPort;

public record VideoStatisticsDTO(int totalVideos, int totalBookmarks, int averageViews) implements VideoStatisticsPort {
}
