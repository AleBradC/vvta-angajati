package AngajatiApp.repository;

import AngajatiApp.controller.DidacticFunction;
import AngajatiApp.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMockTest {

    AngajatiApp.repository.EmployeeMock repoMock;

    @BeforeEach
    void setUp() {
        repoMock = new EmployeeMock();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void modifyEmployeeFunction() {
        Employee mihaela = repoMock.getEmployeeList().stream()
                .filter(emp -> "Dodel".equals(emp.getFirstName()) && "Pacuraru".equals(emp.getLastName()))
                .findFirst()
                .orElse(null);

        assertNotNull(mihaela);

        repoMock.modifyEmployeeFunction(mihaela, DidacticFunction.LECTURER);

        assertEquals(DidacticFunction.LECTURER, mihaela.getFunction());
    }

    @Test
    void modifyEmployeeFunction_withNullEmployee() {
        List<DidacticFunction> originalFunctions = repoMock.getEmployeeList().stream()
                .map(Employee::getFunction)
                .toList();

        repoMock.modifyEmployeeFunction(null, DidacticFunction.LECTURER);

        for (int i = 0; i < repoMock.getEmployeeList().size(); i++) {
            assertEquals(originalFunctions.get(i), repoMock.getEmployeeList().get(i).getFunction());
        }
    }
}