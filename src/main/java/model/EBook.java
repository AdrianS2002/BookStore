package model;

import java.time.LocalDate;

public class EBook{

        private String format;
                private Long id;

        public void setPublishedDate(LocalDate publishedDate) {
                this.publishedDate = publishedDate;
        }

        private String author;

                private String title;

                private LocalDate publishedDate;

                public Long getId() {
                        return id;
                }

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


                @Override
                public String toString(){
                        return String.format("Book author: %s | title: %s | Published Date: %s. | format %s ", author, title, publishedDate,format);
                }
        public String getFormat() {
                return format;
        }

        public void setFormat(String format) {
                this.format = format;
        }
}
