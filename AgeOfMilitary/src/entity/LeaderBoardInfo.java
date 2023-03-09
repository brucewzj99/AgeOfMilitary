package entity;

import java.util.Date;

public class LeaderBoardInfo implements Comparable<LeaderBoardInfo> {

    private String name;
    private Integer score;
    private Date date;

    public LeaderBoardInfo(String name, int score, Date date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(LeaderBoardInfo o) {
        return o.score.compareTo(this.score);

    }

}
