package ano2022.primeirafase;

import util.TestCaseReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Brasilavia {
    private static Group[] groups;

    public static String main(TestCaseReader reader) {
        int numberOfPeople = reader.nextInt();
        initializeGroups(numberOfPeople);

        int numberOfEvents = reader.nextInt();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numberOfEvents; i++) {
            String event = reader.nextString();
            switch (event.toLowerCase()) {
                case "c" -> addToGroup(reader.nextInt(), reader.nextInt());
                case "p" -> registerInfection(reader.nextInt());
                case "n" -> sb.append(findDistinctGroups().size()).append("\n");
                case "ns" -> sb.append(findSafeGroups().size()).append("\n");
                case "ni" -> sb.append(findUnsafeGroups().size()).append("\n");
                case "ii" -> sb.append(findUnsafeGroupsPeople()).append("\n");
                default -> throw new IllegalStateException("Unexpected value: " + event);
            }

        }

        return sb.toString();
    }

    private static String findUnsafeGroupsPeople() {
        List<Group> unsafeGroups = findUnsafeGroups();

        List<Integer> unsafePeople = new ArrayList<>();

        for (Group group : unsafeGroups) {
            unsafePeople.addAll(group.people());
        }

        Collections.sort(unsafePeople);

        if (unsafePeople.isEmpty()) return "vazio";

        StringBuilder sb = new StringBuilder();
        for (int person : unsafePeople) {
            sb.append(person).append(" ");
        }

        return sb.delete(sb.length() - 1, sb.length()).toString();
    }

    private static List<Group> findUnsafeGroups() {
        List<Group> distinctGroups = findDistinctGroups();

        List<Group> unsafeGroups = new ArrayList<>();

        for (Group group : distinctGroups) {
            if (group.isInfected()) {
                unsafeGroups.add(group);
            }
        }

        return unsafeGroups;
    }

    private static List<Group> findSafeGroups() {
        List<Group> distinctGroups = findDistinctGroups();

        List<Group> safeGroups = new ArrayList<>();

        for (Group group : distinctGroups) {
            if (!group.isInfected()) {
                safeGroups.add(group);
            }
        }

        return safeGroups;
    }

    private static List<Group> findDistinctGroups() {
        List<Group> distinctGroups = new ArrayList<>();
        for (Group group : groups) {
            if (!distinctGroups.contains(group)) {
                distinctGroups.add(group);
            }
        }

        return distinctGroups;
    }

    private static void initializeGroups(int numberOfPeople) {
        groups = new Group[numberOfPeople];

        for (int i = 0; i < numberOfPeople; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);

            groups[i] = new Group(temp, false);
        }
    }

    private static void addToGroup(int person, int person2) {
        Group person1Group = groups[person];
        Group person2Group = groups[person2];

        person1Group.people().addAll(person2Group.people());
        groups[person2] = person1Group;
    }

    private static void registerInfection(int personIndex) {
        Group initialGroup = groups[personIndex];

        groups[personIndex] = new Group(initialGroup.people(), true);
    }

    record Group(List<Integer> people, boolean isInfected) {}
}