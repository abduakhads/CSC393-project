package com.example.services;

import com.example.models.Extra;
import com.example.repositories.ExtraRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExtraServiceImpl implements ExtraService {

    private final ExtraRepository extraRepository;

    @Override
    public List<Extra> getAllExtras() {
        return extraRepository.findAll();
    }

    @Override
    public Optional<Extra> getExtraById(Long id) {
        return extraRepository.findById(id);
    }

    @Override
    public Optional<Extra> getExtraByName(String name) {
        return extraRepository.findByName(name);
    }

    @Override
    public Extra saveExtra(Extra extra) {
        return extraRepository.save(extra);
    }

    @Override
    public void deleteExtra(Long id) {
        extraRepository.deleteById(id);
    }
}