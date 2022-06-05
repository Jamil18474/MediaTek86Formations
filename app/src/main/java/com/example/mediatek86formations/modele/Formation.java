package com.example.mediatek86formations.modele;

import com.example.mediatek86formations.outils.MesOutils;

import java.util.Date;

public class Formation implements Comparable {

    /**
     * propriétés privées
     */
    private int id;
    private Date publishedAt;
    private String title;
    private String description;
    private String miniature;
    private String picture;
    private String videoId;

    /**
     * Constructeur de la classe Formation qui valorise les propriétés privées
     * @param id
     * @param publishedAt
     * @param title
     * @param description
     * @param miniature
     * @param picture
     * @param videoId
     */
    public Formation(int id, Date publishedAt, String title, String description, String miniature, String picture, String videoId) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.miniature = miniature;
        this.picture = picture;
        this.videoId = videoId;
    }

    /**
     * retourne l'id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * retourne la date
     * @return date
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * retourne la date en String au format jj/MM/yyyy
     * @return date en String
     */
    public String getPublishedAtToString() {
        return MesOutils.convertDateToString(this.publishedAt);
    }

    /**
     * retourne le titre
     * @return titre
     */
    public String getTitle() {
        return title;
    }

    /**
     * retourne la description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * retourne la miniature
     * @return miniature
     */
    public String getMiniature() {
        return miniature;
    }

    /**
     * retourne l'image
     * @return image
     */
    public String getPicture() {
        return picture;
    }

    /**
     * retourne l'id de la vidéo
     * @return id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return publishedAt.compareTo(((Formation)o).getPublishedAt());
    }

}
