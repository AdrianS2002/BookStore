package model;

import java.time.LocalDate;

public class AudioBook {


    private int runtime;

    private Long id;

    private String author;

    private String title;

    private LocalDate publishedDate;

    public Long getId() {
        return id;}

        public void setId(Long id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LocalDate getPublishedDate() {
            return publishedDate;
        }


        public void setPublishedDate(LocalDate publishedDate) {
            this.publishedDate = publishedDate;
        }

        @Override
        public String toString(){
            return String.format("Book author: %s | title: %s | Published Date: %s | Runtime %d", author, title, publishedDate, runtime);
        }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
