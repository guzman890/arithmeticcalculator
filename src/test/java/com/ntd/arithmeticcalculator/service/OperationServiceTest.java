package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.OperationType;
import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
import com.ntd.arithmeticcalculator.repository.OperationRepository;
import com.ntd.arithmeticcalculator.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    private OperationServiceImpl operationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Guardar una operación")
    public void testSaveOperation() {
        OperationEntity operationEntity = new OperationEntity();
        when(operationRepository.save(any(OperationEntity.class))).thenReturn(operationEntity);

        OperationEntity result = operationService.saveOperation(new OperationEntity());

        assertEquals(operationEntity, result);
        verify(operationRepository).save(any(OperationEntity.class));
    }

    @Test
    @DisplayName("Buscar una operación por ID")
    public void testFindById() {
        OperationEntity operationEntity = new OperationEntity();
        when(operationRepository.findById(1L)).thenReturn(Optional.of(operationEntity));

        Optional<OperationEntity> result = operationService.findById(1L);

        assertEquals(Optional.of(operationEntity), result);
        verify(operationRepository).findById(1L);
    }

    @Test
    @DisplayName("Listar todas las operaciones")
    public void testFindAll() {
        List<OperationEntity> operationEntities = Arrays.asList(new OperationEntity(), new OperationEntity());
        when(operationRepository.findAll()).thenReturn(operationEntities);

        List<OperationEntity> result = operationService.findAll();

        assertEquals(operationEntities, result);
        verify(operationRepository).findAll();
    }

    @Test
    @DisplayName("Actualizar una operación")
    public void testUpdate() {
        OperationEntity existingOperationEntity = new OperationEntity();
        existingOperationEntity.setId(1L);
        existingOperationEntity.setType(OperationType.ADDITION);
        existingOperationEntity.setCost(10D);

        OperationEntity updatedDetails = new OperationEntity();
        updatedDetails.setType(OperationType.ADDITION);
        updatedDetails.setCost(20D);

        when(operationRepository.findById(1L)).thenReturn(Optional.of(existingOperationEntity));
        when(operationRepository.save(any(OperationEntity.class))).thenReturn(updatedDetails);

        Optional<OperationEntity> result = operationService.update(1L, updatedDetails);

        assertEquals(Optional.of(updatedDetails), result);
        verify(operationRepository).findById(1L);
        verify(operationRepository).save(any(OperationEntity.class));
    }

    @Test
    @DisplayName("Eliminar una operación")
    public void testDeleteOperation() {
        doNothing().when(operationRepository).deleteById(1L);

        operationService.deleteOperation(1L);

        verify(operationRepository).deleteById(1L);
    }
}