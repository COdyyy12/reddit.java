import java.util.ArrayList;

public class Post {
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.replyTo = null;
        this.author = author;
        this.upvoteCount = 1;
        this.downvoteCount = 0;
    }

    public Post(String content, Post replyTo, User author) {
        this.title = null;
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        this.upvoteCount = 1;
        this.downvoteCount = 0;
    }

    public String getTitle() {
        return this.title;
    }

    public Post getReplyTo() {
        return this.replyTo;
    }

    public User getAuthor() {
        return this.author;
    }

    public int getUpvoteCount() {
        return this.upvoteCount;
    }

    public int getDownvoteCount() {
        return this.downvoteCount;
    }

    public void updateUpvoteCount(boolean isIncrement) {
        if (isIncrement) {
            this.upvoteCount = this.upvoteCount + 1;
        } else {
            if (this.upvoteCount == 0) {
                return;
            } else {
                this.upvoteCount = this.upvoteCount - 1;
            }
        }
    }

    public void updateDownvoteCount(boolean isIncrement) {
        if (isIncrement) {
            this.downvoteCount = this.downvoteCount + 1;
        } else {
            if (this.downvoteCount == 0) {
                return;
            } else {
                this.downvoteCount = this.downvoteCount - 1;
            }
        }
    }

    public ArrayList<Post> getThread() {
        ArrayList<Post> chain = new ArrayList<>();
        Post cur = this;
        while (cur != null) {
            chain.add(cur);
            cur = cur.replyTo;
        }
        int i = 0;
        int j = chain.size() - 1;
        while (i < j) {
            Post tmp = chain.get(i);
            chain.set(i, chain.get(j));
            chain.set(j, tmp);
            i = i + 1;
            j = j - 1;
        }
        return chain;
    }

    public String toString() {
        if (this.replyTo == null) {
            return "[" + this.upvoteCount + "|" + this.downvoteCount + "]\t"
                 + this.title + "\n\t" + this.content;
        } else {
            return "[" + this.upvoteCount + "|" + this.downvoteCount + "]\t"
                 + this.content;
        }
    }
}

