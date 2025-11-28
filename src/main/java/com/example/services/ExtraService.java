package com.example.services;

import com.example.models.Extra;
import java.util.List;
import java.util.Optional;

public interface ExtraService {
    List<Extra> getAllExtras();
    Optional<Extra> getExtraById(Long id);
    Optional<Extra> getExtraByName(String name);
    Extra saveExtra(Extra extra);
    void deleteExtra(Long id);
}