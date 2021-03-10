/**
 * 
 * 
 * @author Sebastian H
 * @version 1.00 2018/05/26
 */
 

import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Song implements Comparable<Song>
{

	private String song, artist;
	private boolean explicit;

	public Song(String song, String artist)
	{
		this.song = song;
		this.artist = artist;
		explicit = false;
	}
	public Song(String song, String artist, boolean explicit)
	{
		this(song, artist);
		this.explicit = explicit;
	}

	public String getSongName()
	{
		return song;
	}
	public String getArtist()
	{
		return artist;
	}
	public boolean isExplicit()
	{
		return explicit;
	}

	public String toString()
	{
		return toStringSA();
	}
	public String toStringSA()
	{
		return song + " - " + artist;
	}
	public String toStringAS()
	{
		return artist + " - " + song;
	}

	@Override
	public int compareTo(Song o)
	{
		int difference = this.getSongName().compareTo(o.getSongName());
		if (difference == 0)
			difference = this.getArtist().compareTo(o.getArtist());
		return difference;
	}

	@Override
	public boolean equals(Object o)
	{
		return (o instanceof Song) && (compareTo((Song)o) == 0);
	}
}