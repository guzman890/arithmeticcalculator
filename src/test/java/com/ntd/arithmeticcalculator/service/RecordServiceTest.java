package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.RecordEntity;
import com.ntd.arithmeticcalculator.repository.RecordRepository;
import com.ntd.arithmeticcalculator.service.impl.RecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordServiceImpl recordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Guardar un registro")
    public void testSaveRecord() {
        RecordEntity recordEntity = new RecordEntity();
        when(recordRepository.save(any(RecordEntity.class))).thenReturn(recordEntity);

        RecordEntity result = recordService.saveRecord(new RecordEntity());

        assertEquals(recordEntity, result);
        verify(recordRepository).save(any(RecordEntity.class));
    }

    @Test
    @DisplayName("Buscar un registro por ID")
    public void testFindById() {
        RecordEntity recordEntity = new RecordEntity();
        when(recordRepository.findById(1L)).thenReturn(Optional.of(recordEntity));

        Optional<RecordEntity> result = recordService.findById(1L);

        assertEquals(Optional.of(recordEntity), result);
        verify(recordRepository).findById(1L);
    }

    @Test
    @DisplayName("Listar todos los registros")
    public void testFindAll() {
        List<RecordEntity> recordEntities = Arrays.asList(new RecordEntity(), new RecordEntity());
        when(recordRepository.findAll()).thenReturn(recordEntities);

        List<RecordEntity> result = recordService.findAll();

        assertEquals(recordEntities, result);
        verify(recordRepository).findAll();
    }

    @Test
    @DisplayName("Obtener registros paginados")
    public void testGetRecordsPageable() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<RecordEntity> recordEntityList = Arrays.asList(new RecordEntity(), new RecordEntity());
        Page<RecordEntity> expectedPage = new PageImpl<>(recordEntityList, pageable, recordEntityList.size());
        when(recordRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<RecordEntity> result = recordService.getRecords(pageable);

        assertEquals(expectedPage, result);
        verify(recordRepository).findAll(pageable);
    }

    @Test
    @DisplayName("Actualizar un registro")
    public void testUpdate() {
        RecordEntity existingRecordEntity = new RecordEntity();
        existingRecordEntity.setId(1L);

        RecordEntity updatedDetails = new RecordEntity();
        updatedDetails.setId(1L);

        when(recordRepository.findById(1L)).thenReturn(Optional.of(existingRecordEntity));
        when(recordRepository.save(any(RecordEntity.class))).thenReturn(updatedDetails);

        Optional<RecordEntity> result = recordService.update(1L, updatedDetails);

        assertEquals(Optional.of(updatedDetails), result);
        verify(recordRepository).findById(1L);
        verify(recordRepository).save(any(RecordEntity.class));
    }

    @Test
    @DisplayName("Eliminar un registro")
    public void testDeleteRecord() {
        doNothing().when(recordRepository).deleteById(1L);

        recordService.deleteRecord(1L);

        verify(recordRepository).deleteById(1L);
    }
}