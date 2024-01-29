package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoStatisticsPort;

public record VideoStatisticsDTO(int totalVideos, int totalBookmarks, int averageViews) implements VideoStatisticsPort {
}
