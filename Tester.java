public class Tester {
    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        User u1 = new User("CSE11Student");
        User u2 = new User("Helper");

        total++;
        Post p1 = new Post("Hello", "First post", u1);
        u1.addPost(p1);
        if (p1.getReplyTo() == null && p1.getUpvoteCount() == 1 && p1.getDownvoteCount() == 0) {
            passed++; System.out.println("PASS: p1 init");
        } else {
            System.out.println("FAIL: p1 init");
        }

        total++;
        String s1 = p1.toString();
        String e1 = "[1|0]\tHello\n\tFirst post";
        if (s1.equals(e1)) {
            passed++; System.out.println("PASS: p1 toString");
        } else {
            System.out.println("FAIL: p1 toString -> " + s1);
        }

        total++;
        Post c1 = new Post("Nice!", p1, u2);
        u2.addPost(c1);
        String s2 = c1.toString();
        String e2 = "[1|0]\tNice!";
        if (c1.getReplyTo() == p1 && s2.equals(e2)) {
            passed++; System.out.println("PASS: c1 basic");
        } else {
            System.out.println("FAIL: c1 basic");
        }

        total++;
        java.util.ArrayList<Post> t = c1.getThread();
        if (t.size() == 2 && t.get(0) == p1 && t.get(1) == c1) {
            passed++; System.out.println("PASS: thread order");
        } else {
            System.out.println("FAIL: thread order");
        }

        total++;
        u2.upvote(p1);
        if (p1.getUpvoteCount() == 2 && p1.getDownvoteCount() == 0 && u1.getKarma() == 2) {
            passed++; System.out.println("PASS: upvote effect");
        } else {
            System.out.println("FAIL: upvote effect");
        }

        total++;
        u2.upvote(p1);
        if (p1.getUpvoteCount() == 2 && p1.getDownvoteCount() == 0 && u1.getKarma() == 2) {
            passed++; System.out.println("PASS: duplicate upvote ignored");
        } else {
            System.out.println("FAIL: duplicate upvote ignored");
        }

        total++;
        u2.downvote(p1);
        if (p1.getUpvoteCount() == 1 && p1.getDownvoteCount() == 1 && u1.getKarma() == 0) {
            passed++; System.out.println("PASS: switch to downvote");
        } else {
            System.out.println("FAIL: switch to downvote");
        }

        total++;
        u1.upvote(p1);
        if (p1.getUpvoteCount() == 1 && p1.getDownvoteCount() == 1) {
            passed++; System.out.println("PASS: self vote ignored");
        } else {
            System.out.println("FAIL: self vote ignored");
        }

        total++;
        Post p2 = new Post("Second", "Another", u1);
        u1.addPost(p2);
        if (u1.getTopPost() == p2) {
            passed++; System.out.println("PASS: topPost p2");
        } else {
            System.out.println("FAIL: topPost p2");
        }

        total++;
        u2.downvote(p2);
        if (p2.getUpvoteCount() == 1 && p2.getDownvoteCount() == 1 && u1.getTopPost() == p1) {
            passed++; System.out.println("PASS: tie picks earlier");
        } else {
            System.out.println("FAIL: tie picks earlier");
        }

        total++;
        if (u2.getTopComment() == c1) {
            passed++; System.out.println("PASS: topComment");
        } else {
            System.out.println("FAIL: topComment");
        }

        System.out.println("Passed " + passed + " / " + total);
    }
}
