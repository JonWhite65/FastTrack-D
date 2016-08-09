package com.cooksys.collections.assignment.test;

import com.cooksys.collections.Employee;
import com.cooksys.collections.Grunt;
import com.cooksys.collections.ManagementHierarchy;
import com.cooksys.collections.Manager;
import com.cooksys.collections.assignment.ConcreteManagementHierarchy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ConcreteManagementHierarchyTests {
    private ManagementHierarchy hierarchy;

    private Set<Manager> managers;
    private Set<Employee> bradUnderlings;
    private Set<Employee> frankUnderlings;
    private Set<Employee> edUnderlings;
    private Set<Employee> underlings;
    private Set<Employee> employees;

    private Map<Manager, Set<Employee>> map;
    private List<Manager> peterChain;
    private List<Manager> chrisChain;

    private Manager brad;

    private Manager frank;

    private Grunt peter;
    private Grunt michael;
    private Grunt dillon;

    private Manager ed;

    private Grunt mandy;
    private Grunt chris;

    @Before
    public void setUp() throws Exception {
        hierarchy = new ConcreteManagementHierarchy();
        brad = new Manager("Brad", "COO", null);

        frank = new Manager("Frank", "FastTrack'D Manager", brad);
        peter = new Grunt("Peter", "Software Architect", frank);
        michael = new Grunt("Michael", "Lead Instructor", frank);
        dillon = new Grunt("Dillon", "Instructor-in-Training", frank);

        ed = new Manager("Ed", "Recruitment and Placement Manager", brad);
        mandy = new Grunt("Mandy", "FastTrack'D Recruiter", ed);
        chris = new Grunt("Chris", "Student Advocate", ed);

        managers = new HashSet<>();
        managers.add(brad);
        managers.add(frank);
        managers.add(ed);

        bradUnderlings = new HashSet<>();
        bradUnderlings.add(frank);
        bradUnderlings.add(ed);

        frankUnderlings = new HashSet<>();
        frankUnderlings.add(peter);
        frankUnderlings.add(michael);
        frankUnderlings.add(dillon);

        edUnderlings = new HashSet<>();
        edUnderlings.add(mandy);
        edUnderlings.add(chris);

        underlings = new HashSet<>();
        underlings.addAll(bradUnderlings);
        underlings.addAll(frankUnderlings);
        underlings.addAll(edUnderlings);

        employees = new HashSet<>();
        employees.addAll(managers);
        employees.addAll(underlings);

        map = new HashMap<>();
        map.put(brad, bradUnderlings);
        map.put(frank, frankUnderlings);
        map.put(ed, edUnderlings);

        peterChain = new ArrayList<>();
        peterChain.add(frank);
        peterChain.add(brad);

        chrisChain = new ArrayList<>();
        chrisChain.add(ed);
        chrisChain.add(brad);
    }

    @After
    public void tearDown() throws Exception {
        hierarchy = null;
        brad = null;
        frank = null;
        peter = null;
        michael = null;
        dillon = null;
        ed = null;
        mandy = null;
        chris = null;
        managers = null;
        bradUnderlings = null;
        frankUnderlings = null;
        edUnderlings = null;
        underlings = null;
        employees = null;
        map = null;
        peterChain = null;
        chrisChain = null;
    }

    @Test
    public void addManagerTest() throws Exception {
        assertTrue(hierarchy.addManager(brad));
        assertFalse(hierarchy.addManager(brad));

        assertTrue(hierarchy.addManager(frank));
        assertFalse(hierarchy.addManager(frank));

        assertTrue(hierarchy.addManager(ed));
        assertFalse(hierarchy.addManager(ed));
    }

    @Test
    public void getManagersTest() throws Exception {
        assertNotNull(hierarchy.getManagers());
        assertTrue(hierarchy.getManagers().size() == 0);

        for (Manager m : managers) {
            hierarchy.addManager(m);
        }

        assertFalse(hierarchy.getManagers().isEmpty());
        assertTrue(hierarchy.getManagers().size() == 3);
        assertTrue(hierarchy.getManagers().equals(managers));
    }

    @Test
    public void hasManagerTest() throws Exception {
        assertFalse(hierarchy.hasManager(brad));
        hierarchy.addManager(brad);
        assertTrue(hierarchy.hasManager(brad));
    }

    @Test
    public void getUnderlingsTest() throws Exception {
        assertNotNull(hierarchy.getUnderlings(brad));
        assertTrue(hierarchy.getUnderlings(brad).isEmpty());

        for (Manager m : managers) {
            hierarchy.addManager(m);
        }

        for (Employee e : underlings) {
            hierarchy.addEmployee(e);
        }

        assertTrue(hierarchy.getUnderlings(brad).equals(bradUnderlings));
        assertTrue(hierarchy.getUnderlings(frank).equals(frankUnderlings));
        assertTrue(hierarchy.getUnderlings(ed).equals(edUnderlings));
    }

    @Test
    public void addEmployeeTest() throws Exception {
        assertFalse(hierarchy.addEmployee(brad));
        assertFalse(hierarchy.addEmployee(frank));

        hierarchy.addManager(brad);
        assertTrue(hierarchy.addEmployee(frank));
        assertFalse(hierarchy.addEmployee(frank));
        assertFalse(hierarchy.addEmployee(mandy));
    }

    @Test
    public void getEmployeesTest() throws Exception {
        assertNotNull(hierarchy.getEmployees());
        assertTrue(hierarchy.getEmployees().isEmpty());

        for (Manager m : managers) {
            hierarchy.addManager(m);
        }

        for (Employee e : employees) {
            hierarchy.addEmployee(e);
        }

        assertTrue(hierarchy.getEmployees().equals(employees));
    }

    @Test
    public void hasEmployeeTest() throws Exception {
        assertFalse(hierarchy.hasEmployee(brad));
        hierarchy.addManager(brad);
        assertTrue(hierarchy.hasEmployee(brad));
        hierarchy.addEmployee(frank);
        assertTrue(hierarchy.hasEmployee(frank));
    }

    @Test
    public void getHierarchyTest() throws Exception {
        assertNotNull(hierarchy.getHierarchy());
        assertTrue(hierarchy.getHierarchy().isEmpty());
        for (Manager m : managers) {
            hierarchy.addManager(m);
        }
        for (Employee e : employees) {
            hierarchy.addEmployee(e);
        }
        assertTrue(hierarchy.getHierarchy().equals(map));
    }

    @Test
    public void getChainOfCommandTest() throws Exception {
        assertNotNull(hierarchy.getChainOfCommand(peter));
        assertTrue(hierarchy.getChainOfCommand(peter).isEmpty());
        for (Manager m : managers) {
            hierarchy.addManager(m);
        }
        for (Employee e : employees) {
            hierarchy.addEmployee(e);
        }
        assertNotNull(hierarchy.getChainOfCommand(brad));
        assertTrue(hierarchy.getChainOfCommand(brad).isEmpty());
        assertTrue(hierarchy.getChainOfCommand(peter).equals(peterChain));
        assertTrue(hierarchy.getChainOfCommand(chris).equals(chrisChain));
    }
}
