package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.Record;
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
        Record record = new Record();
        when(recordRepository.save(any(Record.class))).thenReturn(record);

        Record result = recordService.saveRecord(new Record());

        assertEquals(record, result);
        verify(recordRepository).save(any(Record.class));
    }

    @Test
    @DisplayName("Buscar un registro por ID")
    public void testFindById() {
        Record record = new Record();
        when(recordRepository.findById(1L)).thenReturn(Optional.of(record));

        Optional<Record> result = recordService.findById(1L);

        assertEquals(Optional.of(record), result);
        verify(recordRepository).findById(1L);
    }

    @Test
    @DisplayName("Listar todos los registros")
    public void testFindAll() {
        List<Record> records = Arrays.asList(new Record(), new Record());
        when(recordRepository.findAll()).thenReturn(records);

        List<Record> result = recordService.findAll();

        assertEquals(records, result);
        verify(recordRepository).findAll();
    }

    @Test
    @DisplayName("Obtener registros paginados")
    public void testGetRecordsPageable() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<Record> recordList = Arrays.asList(new Record(), new Record());
        Page<Record> expectedPage = new PageImpl<>(recordList, pageable, recordList.size());
        when(recordRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Record> result = recordService.getRecords(pageable);

        assertEquals(expectedPage, result);
        verify(recordRepository).findAll(pageable);
    }

    @Test
    @DisplayName("Actualizar un registro")
    public void testUpdate() {
        Record existingRecord = new Record();
        existingRecord.setId(1L);

        Record updatedDetails = new Record();
        updatedDetails.setId(1L);

        when(recordRepository.findById(1L)).thenReturn(Optional.of(existingRecord));
        when(recordRepository.save(any(Record.class))).thenReturn(updatedDetails);

        Optional<Record> result = recordService.update(1L, updatedDetails);

        assertEquals(Optional.of(updatedDetails), result);
        verify(recordRepository).findById(1L);
        verify(recordRepository).save(any(Record.class));
    }

    @Test
    @DisplayName("Eliminar un registro")
    public void testDeleteRecord() {
        doNothing().when(recordRepository).deleteById(1L);

        recordService.deleteRecord(1L);

        verify(recordRepository).deleteById(1L);
    }
}