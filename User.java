import java.util.ArrayList;

public class User {
    private String username;
    private int karma;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    public User(String username) {
        this.username = username;
        this.karma = 0;
        this.posts = new ArrayList<>();
        this.upvoted = new ArrayList<>();
        this.downvoted = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public int getKarma() {
        return this.karma;
    }

    public ArrayList<Post> getPosts() {
        return this.posts;
    }

    public void addPost(Post post) {
        if (post == null) {
            return;
        }
        if (post.getAuthor() != this) {
            return;
        }
        this.posts.add(post);
        updateKarma();
    }

    public void updateKarma() {
        int total = 0;
        for (int i = 0; i < this.posts.size(); i++) {
            Post p = this.posts.get(i);
            total += p.getUpvoteCount() - p.getDownvoteCount();
        }
        this.karma = total;
    }

    public void upvote(Post post) {
        if (post == null) {
            return;
        }
        if (post.getAuthor() == this) {
            return;
        }
        if (this.upvoted.contains(post)) {
            return;
        }
        if (this.downvoted.contains(post)) {
            this.downvoted.remove(post);
            post.updateDownvoteCount(false);
        }
        this.upvoted.add(post);
        post.updateUpvoteCount(true);
        User author = post.getAuthor();
        if (author != null) {
            author.updateKarma();
        }
    }

    public void downvote(Post post) {
        if (post == null) {
            return;
        }
        if (post.getAuthor() == this) {
            return;
        }
        if (this.downvoted.contains(post)) {
            return;
        }
        if (this.upvoted.contains(post)) {
            this.upvoted.remove(post);
            post.updateUpvoteCount(false);
        }
        this.downvoted.add(post);
        post.updateDownvoteCount(true);
        User author = post.getAuthor();
        if (author != null) {
            author.updateKarma();
        }
    }

    public Post getTopPost() {
        Post best = null;
        int bestScore = 0;
        boolean found = false;
        for (int i = 0; i < this.posts.size(); i++) {
            Post p = this.posts.get(i);
            if (p.getReplyTo() == null) {
                int score = p.getUpvoteCount() - p.getDownvoteCount();
                if (!found || score > bestScore) {
                    best = p;
                    bestScore = score;
                    found = true;
                }
            }
        }
        return best;
    }

    public Post getTopComment() {
        Post best = null;
        int bestScore = 0;
        boolean found = false;
        for (int i = 0; i < this.posts.size(); i++) {
            Post p = this.posts.get(i);
            if (p.getReplyTo() != null) {
                int score = p.getUpvoteCount() - p.getDownvoteCount();
                if (!found || score > bestScore) {
                    best = p;
                    bestScore = score;
                    found = true;
                }
            }
        }
        return best;
    }

    public String toString() {
        return "u/" + this.username + " Karma: " + this.karma;
    }
}
