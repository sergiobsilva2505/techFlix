package br.com.fiap.techFlix.application.ports;

import java.util.List;

public interface VideoPublishPort {

    String fileId();

    String title();

    String description();

    List<String> categoryNames();
}
