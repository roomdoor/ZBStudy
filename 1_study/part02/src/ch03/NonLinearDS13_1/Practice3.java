package ch03.NonLinearDS13_1;

import java.util.*;

public class Practice3 {
    public static ArrayList<ArrayList<String>> solution(ArrayList<ArrayList<String>> accounts) {
        Map<String, Set<String>> merge = new HashMap<>();
        Map<String, String> usernames = new HashMap<>();

        for (ArrayList<String> account : accounts) {
            String username = account.get(0);
            if (!merge.containsKey(username)) {
                merge.put(username, new HashSet<>());
                for (int i = 1; i < account.size(); i++) {
                    merge.get(username).add(account.get(i));
                    usernames.put(username, username);
                }
            } else {
                for (int j = 1; j < account.size(); j++) {
                    if (merge.get(username).contains(account.get(j))) {
                        for (int i = 1; i < account.size(); i++) {
                            merge.get(username).add(account.get(i));
                        }
                        break;
                    } else {
                        merge.put(username + "*", new HashSet<>());
                        for (int i = 1; i <account.size() ; i++) {
                            merge.get(username + "*").add(account.get(i));
                        }
                        usernames.put(username + "*", username);
                    }
                }
            }
        }

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int i = 0;
        for (String username : usernames.keySet()) {
            result.add(new ArrayList<>());
            result.get(i).add(usernames.get(username));
            result.get(i).addAll(merge.get(username));
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        // Test code
        ArrayList<ArrayList<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "john@mail.com", "john_lab@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "john@mail.com", "john02@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));
        accounts = solution(accounts);
        for (ArrayList<String> item : accounts) {
            System.out.println(item);
        }
    }
}
