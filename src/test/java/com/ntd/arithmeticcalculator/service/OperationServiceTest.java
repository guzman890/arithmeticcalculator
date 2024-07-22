package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.OperationType;
import com.ntd.arithmeticcalculator.model.entity.Operation;
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
        Operation operation = new Operation();
        when(operationRepository.save(any(Operation.class))).thenReturn(operation);

        Operation result = operationService.saveOperation(new Operation());

        assertEquals(operation, result);
        verify(operationRepository).save(any(Operation.class));
    }

    @Test
    @DisplayName("Buscar una operación por ID")
    public void testFindById() {
        Operation operation = new Operation();
        when(operationRepository.findById(1L)).thenReturn(Optional.of(operation));

        Optional<Operation> result = operationService.findById(1L);

        assertEquals(Optional.of(operation), result);
        verify(operationRepository).findById(1L);
    }

    @Test
    @DisplayName("Listar todas las operaciones")
    public void testFindAll() {
        List<Operation> operations = Arrays.asList(new Operation(), new Operation());
        when(operationRepository.findAll()).thenReturn(operations);

        List<Operation> result = operationService.findAll();

        assertEquals(operations, result);
        verify(operationRepository).findAll();
    }

    @Test
    @DisplayName("Actualizar una operación")
    public void testUpdate() {
        Operation existingOperation = new Operation();
        existingOperation.setId(1L);
        existingOperation.setType(OperationType.ADDITION);
        existingOperation.setCost(10D);

        Operation updatedDetails = new Operation();
        updatedDetails.setType(OperationType.ADDITION);
        updatedDetails.setCost(20D);

        when(operationRepository.findById(1L)).thenReturn(Optional.of(existingOperation));
        when(operationRepository.save(any(Operation.class))).thenReturn(updatedDetails);

        Optional<Operation> result = operationService.update(1L, updatedDetails);

        assertEquals(Optional.of(updatedDetails), result);
        verify(operationRepository).findById(1L);
        verify(operationRepository).save(any(Operation.class));
    }

    @Test
    @DisplayName("Eliminar una operación")
    public void testDeleteOperation() {
        doNothing().when(operationRepository).deleteById(1L);

        operationService.deleteOperation(1L);

        verify(operationRepository).deleteById(1L);
    }
}