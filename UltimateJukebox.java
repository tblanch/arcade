/**
 * 
 * 
 * @author Sebastian H
 * @version 1.00 2018/05/22
 */
 

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class UltimateJukebox extends JPanel
{

	/**
	 * This holds the name and artist of the songs in our library.
	 * File addresses are based on these so the entries should follow the proper format:<br />
	 * <b><code>[song name] + " - " + [artist name]</code></b><br />
	 */
	private Song[] hitSongs, classicSongs;
	
	/** Current <code>Song</code> being controlled */
	private Song currentSong;
	/** <code>Clip</code> of current song being played */
	private Clip clip;
		
	// Constructor
	public UltimateJukebox()
	{
		// SET MUSIC LIBRARY
		
		classicSongs = new Song[] {
				JUKEBOX_SWITCH,
				new Song("Sandstorm (VORN Remix)", "Darude")
		};
		
		hitSongs = new Song[] {
				JUKEBOX_SWITCH,
			// 2018
				new Song("Jump", "Julia Michaels ft. Trippie Redd", true),
				new Song("This Is America", "Childish Gambino", true),
				new Song("Never Be The Same", "Camila Cabello"),
				new Song("The Middle", "Zedd, Maren Morris & Grey"),
				new Song("Whatever It Takes", "Imagine Dragons"),
				new Song("Wait", "Maroon 5"),
				new Song("FRIENDS", "Marshmello & Anne-Marie", true),
				new Song("In My Blood", "Shawn Mendes"),
				new Song("No Tears Left To Cry", "Ariana Grande"),
				new Song("New Rules", "Dua Lipa"),
				new Song("Delicate", "Taylor Swift"),
				new Song("IDGAF", "Dua Lipa", true),
				new Song("One Kiss", "Calvin Harris & Dua Lipa"),
				new Song("Done For Me", "Charlie Puth ft. Kehlani"),
				new Song("Finesse", "Bruno Mars ft. Cardi B"),
				new Song("Love Lies", "Khalid & Normani"),
				new Song("Pray For Me", "The Weeknd & Kendrick Lamar", true),
				new Song("God's Plan", "Drake", true),
				new Song("Psycho", "Post Malone ft. Ty Dolla $ign", true),
				new Song("All The Stars", "Kendrick Lamar & SZA", true),
				new Song("Nice For What", "Drake", true),
				new Song("Everyday", "Logic & Marshmello", true),
				new Song("Familiar", "Liam Payne & J Balvin"),
				new Song("These Days", "Rudimental ft. Jess Glynne, Macklemore & Dan Caplen"),
				new Song("No Excuses", "Meghan Trainor"),
				new Song("Want You Back", "5 Seconds Of Summer", true),
				new Song("Pray", "Sam Smith ft. Logic", true),
				new Song("Perfect", "Ed Sheeran"),
				new Song("Feel It Still", "Portugal. The Man"),
				new Song("There's Nothing Holdin' Me Back", "Shawn Mendes"),
				new Song("What About Us", "P!nk"),
				new Song("Havana", "Camila Cabello ft. Young Thug"),
				new Song("Meant To Be", "Bebe Rexha & Florida Georgia Line"),
				new Song("Thunder", "Imagine Dragons"),
				new Song("Attention", "Charlie Puth"),
				new Song("Beautiful Trauma", "P!nk", true),
				new Song("Ashes", "Celine Dion"),
				new Song("Sorry Not Sorry", "Demi Lovato", true),
				new Song("Lights Down Low", "MAX ft. gnash"),
				new Song("Good Old Days", "Macklemore ft. Kesha"),
				new Song("I Don't Think About You", "Kelly Clarkson"),
				new Song("Leave A Light On", "Tom Walker"),
				new Song("Freaky Friday", "Lil Dicky ft. Chris Brown", true),
				new Song("How Long", "Charlie Puth"),
			// 2017
				new Song("Despacito", "Luis Fonsi & Daddy Yankee ft. Justin Bieber"),
				new Song("That's What I Like", "Bruno Mars"),
				new Song("I'm The One", "DJ Khaled ft. Justin Bieber, Quavo, Chance The Rapper & Lil Wayne", true),
				new Song("Wild Thoughts", "DJ Khaled ft. Rihanna & Bryson Tiller", true),
				new Song("Shape of You", "Ed Sheeran"),
				new Song("Believer", "Imagine Dragons"),
				new Song("HUMBLE.", "Kendrick Lamar", true),
				new Song("Body Like A Back Road", "Sam Hunt"),
				new Song("Congratulations", "Post Malone ft. Quavo", true),
				new Song("Something Just Like This", "The Chainsmokers & Coldplay"),
				new Song("XO TOUR Llif3", "Lil Uzi Vert", true),
				new Song("Stay", "Zedd & Alessia Cara"),
				new Song("Mask Off", "Future", true),
				new Song("Redbone", "Childish Gambino"),
				new Song("It Ain't Me", "Kygo & Selena Gomez"),
				new Song("Bad And Boujee", "Migos ft. Lil Uzi Vert", true),
				new Song("Closer", "The Chainsmokers ft. Halsey"),
				new Song("24K Magic", "Bruno Mars"),
				new Song("Black Beatles", "Rae Sremmurd ft. Gucci Mane", true),
				new Song("Starboy", "The Weeknd ft. Daft Punk", true),
				new Song("Bodak Yellow (Money Moves)", "Cardi B", true),
				/*new Song("Location", "Khalid"),
				new Song("I Dont Wanna Live Forever", "Zayn & Taylor Swift"),
				new Song("Issues", "Julia Michaels"),
				new Song("1-800-273-8255", "Logic ft. Alessia Cara & Khalid"),
				new Song("I Feel It Coming", "The Weeknd ft. Daft Punk"),
				new Song("Strip That Down", "Liam Payne ft. Quavo"),
				new Song("Don't Wanna Know", "Maroon 5 ft. Kendrick Lamar"),
				new Song("Castle On The Hill", "Ed Sheeran"),
				new Song("Side to Side", "Ariana Grande ft. Nicki Minaj"),
				new Song("Rockabye", "Clean Bandit ft. Sean Paul & Anne-Marie"),
				new Song("Can't Stop The Feeling!", "Justin Timberlake"),
				new Song("Mi Gente", "J Balvin & Willy William ft. Beyonce"),
				new Song("Rockstar", "Post Malone ft. 21 Savage"),
				new Song("Heathens", "twenty one pilots"),
				new Song("DNA.", "Kendrick Lamar"),
				new Song("Loyalty.", "Kendrick Lamar ft. Rihanna"),
				new Song("Praying", "Kesha"),
				new Song("Cold", "Maroon 5 ft. Future"),
				new Song("Broccoli", "D.R.A.M. ft. Lil Yachty"),
				new Song("Chained To The Rhythm", "Katy Perry ft. Skip Marley"),
				new Song("Feels", "Calvin Harris ft. Pharrell Williams, Katy Perry & Big Sean"),
				new Song("Too Good At Goodbyes", "Sam Smith"),
				new Song("Young Dumb & Broke", "Khalid"),
				new Song("Starving", "Hailee Steinfeld & Grey ft. Zedd"),
				new Song("Slippery", "Migos ft. Gucci Mane"),
				new Song("Sign Of The Times", "Harry Styles"),
				new Song("Malibu", "Miley Cyrus"),
				new Song("Down", "Marian Hill"),
				new Song("No Promises", "Cheat Codes ft. Demi Lovato"),
				new Song("Treat You Better", "Shawn Mendes"),
				new Song("I Get The Bag", "Gucci Mane ft. Migos"),
				new Song("What Lovers Do", "Maroon 5 ft. SZA"),
				new Song("Do Re Mi", "Blackbear"),
			// 2016
				new Song("Sorry", "Justin Bieber"),
				new Song("One Dance", "Drake ft. WizKid & Kyla"),
				new Song("Work", "Rihanna"),
				new Song("Stressed Out", "twenty one pilots"),
				new Song("Panda", "Desiigner"),
				new Song("Hello", "Adele"),
				new Song("Cheap Thrills", "Sia ft. Sean Paul"),
				new Song("7 Years", "Lukas Graham"),
				new Song("Me, Myself & I", "G-Eazy & Bebe Rexha"),
				new Song("Stitches", "Shawn Mendes"),
				new Song("Hotline Bling", "Drake"),
				new Song("Roses", "The Chainsmokers ft. Rozes"),
				new Song("Too Good", "Drake ft. Rihanna"),
				new Song("The Hills", "The Weeknd"),
				new Song("What Do You Mean?", "Justin Bieber"),
				new Song("One Call Away", "Charlie Puth"),
				new Song("We Don't Talk Anymore", "Charlie Puth"),
				new Song("In The Night", "The Weeknd"),
				new Song("White Iverson", "Post Malone"),
				new Song("Can't Feel My Face", "The Weeknd"),
				new Song("Lean On", "Major Lazer & DJ Snake ft. MO"),
				new Song("Adventure Of A Lifetime", "Coldplay"),
				new Song("See You Again", "Wiz Khalifa ft. Charlie Puth"),
			// 2015
				new Song("Uptown Funk!", "Mark Ronson ft. Bruno Mars"),
				new Song("Thinking Out Loud", "Ed Sheeran"),
				new Song("Trap Queen", "Fetty Wap"),
				new Song("Sugar", "Maroon 5"),
				new Song("Blank Space", "Taylor Swift"),
				new Song("Earned It", "The Weeknd"),
				new Song("Cheerleader", "OMI"),
				new Song("Take Me To Church", "Hozier"),
				new Song("Bad Blood", "Taylor Swift ft. Kendrick Lamar"),
				new Song("Shake It Off", "Taylor Swift"),
				new Song("Lips Are Movin", "Meghan Trainor"),
				new Song("Honey, I'm Good.", "Andy Grammar"),
				new Song("All About That Bass", "Meghan Trainor"),
				new Song("Locked Away", "R. City ft. Adam Levine"),
				new Song("FourFiveSeconds", "Rihanna & Kanye West & Paul McCartney"),
				new Song("Centuries", "Fall Out Boy"),
				new Song("I Don't F**k With You", "Big Sean ft. E-40"),
				new Song("Elastic Heart", "Sia"),
				new Song("Stay With Me", "Sam Smith"),
				new Song("Dear Future Husband", "Meghan Trainor"),
				new Song("Habits (Stay High)", "Tove Lo"),
				new Song("Bang Bang", "Jessie J, Ariana Grande & Nicki Minaj"),
				new Song("Downtown", "Macklemore & Ryan Lewis ft. Eric Nally, Melle Mel, Kool Moe Dee, Grandmaster Caz"),
				new Song("Chandelier", "Sia"),
				new Song("Ghost", "Ella Henderson"),
				new Song("Waves", "Mr. Probz"),
			// 2014
				new Song("Happy", "Pharrell Williams"),
				new Song("Dark Horse", "Katy Perry"),
				new Song("All Of Me", "John Legend"),
				new Song("Fancy", "Iggy Azalea ft. Charli XCX"),
				new Song("Counting Stars", "OneRepublic"),
				new Song("Rude", "MAGIC!"),
				new Song("Problem", "Ariana Grande ft. Iggy Azalea"),
				new Song("Timber", "Pitbull ft. Ke$ha"),
				new Song("Pompeii", "Bastille"),
				new Song("Am I Wrong", "Nico & Vinz"),
				new Song("Turn Down For What", "DJ Snake & Lil Jon"),
				new Song("The Monster", "Eminem ft. Rihanna"),
				new Song("Say Something", "A Great Big World & Christina Aguilera"),
				new Song("Team", "Lorde"),
				new Song("Royals", "Lorde"),
				new Song("Wake Me Up!", "Avicii"),
				new Song("Demons", "Imagine Dragons"),
				new Song("Maps", "Maroon 5"),
				new Song("Best Day Of My Life", "American Authors"),
				new Song("Summer", "Calvin Harris"),
				new Song("Anaconda", "Nicki Minaj"),
				new Song("Rather Be", "Clean Bandit ft. Jess Glynne"),
				new Song("Wrecking Ball", "Miley Cyrus"),
				new Song("Roar", "Katy Perry"),
				new Song("Ain't It Fun", "Paramore"),
				new Song("Radioactive", "Imagine Dragons"),
				new Song("Love Runs Out", "OneRepublic"),
				new Song("Brave", "Sara Bareilles"),
				new Song("Blurred Lines", "Robin Thicke ft. T.I. & Pharrell"),
			// 2013
				new Song("Thrift Shop", "Macklemore & Ryan Lewis ft. Wanz"),
				new Song("Can't Hold Us", "Macklemore & Ryan Lewis ft. Ray Dalton"),
				new Song("When I Was Your Man", "Bruno Mars"),
				new Song("Locked Out Of Heaven", "Bruno Mars"),
				new Song("Stay", "Rihanna ft. Mikky Ekko"),
				new Song("Get Lucky", "Daft Punk ft. Pharrell Williams"),
				new Song("Clarity", "Zedd ft. Foxes"),
				new Song("Sail", "AWOLNATION"),
				new Song("Diamonds", "Rihanna"),
				new Song("Treasure", "Bruno Mars"),
				new Song("One More Night", "Maroon 5"),
				new Song("My Songs Know What You Did In The Dark (Light Em Up)", "Fall Out Boy"),
				new Song("Same Love", "Macklemore & Ryan Lewis ft. Mary Lambert"),
				new Song("Girl On Fire", "Alicia Keys ft. Nicki Minaj"),
				new Song("Try", "P!nk"),
				new Song("Gangnam Style", "PSY"),
				new Song("Some Nights", "fun."),
				new Song("Carry On", "fun."),
			// 2012
				new Song("Somebody That I Used To Know", "Gotye ft. Kimbra"),
				new Song("Call Me Maybe", "Carly Rae Jepsen"),
				new Song("We Are Young", "fun."),
				new Song("Payphone", "Maroon 5 ft. Wiz Khalifa"),
				new Song("Lights", "Ellie Goulding"),
				new Song("Stronger (What Doesn't Kill You)", "Kelly Clarkson"),
				new Song("We Found Love", "Rihanna ft. Calvin Harris"),
				new Song("Starships", "Nicki Minaj"),
				new Song("Titanium", "David Guetta ft. Sia"),
				new Song("Party Rock Anthem", "LMFAO ft. Lauren Bennett & GoonRock"),
				new Song("Moves Like Jagger", "Marron 5 ft. Christina Aguilera"),
				new Song("Without You", "David Guetta ft. Usher"),
				new Song("Stereo Hearts", "Gym Class Heroes ft. Adam Levine"),
				new Song("Blown Away", "Carrie Underwood"),
				new Song("Rolling In The Deep", "Adele"),
				new Song("Die Young", "Ke$ha"),
			// 2011
				new Song("Firework", "Katy Perry"),
				new Song("Grenade", "Bruno Mars"),
				new Song("Super Bass", "Nicki Minaj"),
				new Song("Pumped Up Kicks", "Foster The People"),
				new Song("Last Friday Night (T.G.I.F.)", "Katy Perry"),
				new Song("Just The Way You Are", "Bruno Mars"),
				new Song("Someone Like You", "Adele"),
				new Song("The Lazy Song", "Bruno Mars"),
				new Song("If I Die Young", "The Band Perry"),
				new Song("Only Girl (In The World)", "Rihanna"),
				new Song("Dynamite", "Taio Cruz"),
				new Song("Jar Of Hearts", "Cristina Perri"),
				new Song("Sexy And I Know It", "LMFAO"),
				new Song("Teenage Dream", "Katy Perry"),
				new Song("Price Tag", "Jessie J ft. B.o.B"),
			// 2010
				new Song("TiK ToK", "Ke$ha"),
				new Song("Hey, Soul Sister", "Train"),
				new Song("California Gurls", "Katy Perry ft. Snoop Dogg"),
				new Song("Airplanes", "B.o.B ft. Hayley Williams"),
				new Song("Love The Way You Lie", "Eminem ft. Rihanna"),
				new Song("Bad Romance", "Lady Gaga"),
				new Song("DJ Got Us Fallin' In Love", "Usher ft. Pitbull"),
				new Song("Billionaire", "Travie McCoy ft. Bruno Mars"),
				new Song("Fireflies", "Owl City"),
				new Song("Party In The U.S.A.", "Miley Cyrus"),
				new Song("All I Do Is Win", "DJ Khaled ft. T-Pain, Ludacris, Jay Sean & Lil Wayne"),
			// 2009
				new Song("Poker Face", "Lady Gaga"),
				new Song("I Gotta Feeling", "The Black Eyes Peas"),
				new Song("I'm Yours", "Jason Mraz"),
				new Song("Single Ladies (Put A Ring On It)", "Beyonce"),
				new Song("Whatcha Say", "Jason Derulo"),
				new Song("Paparazzi", "Lady Gaga"),
				new Song("Viva La Vida", "Coldplay"),
			// 2008
				new Song("Love Song", "Sara Bareilles"),
				new Song("I Kissed A Girl", "Katy Perry"),
				new Song("Don't Stop The Music", "Rihanna"),
			// 2007
				new Song("Umbrella", "Rihanna ft. Jay-Z"),
				new Song("Hey There Delilah", "Plain White T's"),
				new Song("Fergalicious", "Fergie"),
			// 2006
				new Song("Hips Don't Lie", "Shakira ft. Wyclef Jean"),
		*/};
		
		Arrays.sort(hitSongs);
	}
	
	public boolean clipExists()
	{
		return clip != null;
	}
	public void play()
	{
		clip.start();
	}
	public void pause()
	{
		if (clip == null)
			return;
		clip.stop();
	}
	public void jump(int changeInSeconds)
	{
		long shift = changeInSeconds * 1000000;
		long newPosition = clip.getMicrosecondPosition() + shift;
		clip.setMicrosecondPosition(newPosition);
	}
	public void restart()
	{
		clip.setMicrosecondPosition(0);
		clip.start();
	}
	public void repeat(boolean repeat)
	{
		if (repeat)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			clip.loop(0);
	}
	public String getTime()
	{
		return clip.getMicrosecondPosition()/10000/100.0 + " / " + clip.getMicrosecondLength()/10000/100.0;
	}
	
	public boolean setCurrentSong(Song s)
	{
		currentSong = s;
		clip = getClip(currentSong);
		return clipExists();
	}
	/*public boolean setCurrentSong(int index)
	{
		return setCurrentSong(hitSongs[index]);
	}
	public boolean setCurrentSong(String song, String artist)
	{
		int index = -1;
		for (int i = 0; i < hitSongs.length; i++)
		{
			if (hitSongs[i].equals(new Song(song, artist)))
				index = i;
			//System.out.println(hitSongs[i]);
			//System.out.println(new Song(song, artist));
		}
		if (index == -1)
		{
			JOptionPane.showMessageDialog(
					null,
					"This song is not in our library at the moment. We are sorry for the inconvenience.",
					"Song not found (bad input)",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return setCurrentSong(index);
	}
	public void setCurrentSong(String songInfo)
	{
		setCurrentSong(songInfo.substring(0, songInfo.indexOf(" - ")), songInfo.substring(songInfo.indexOf(" - ")+3));
	}*/
	
	public Clip getClip(Song s)
	{
		try
		{
			// Open an audio input stream.
			InputStream url = this.getClass().getClassLoader().getResourceAsStream("UltimateJukebox/music/" + s.getArtist() + " - " + s.getSongName() + ".wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			return clip;
		}/* catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}*/ catch (Exception e)
		{
			JOptionPane.showMessageDialog(
					null,
					"This song is not in our library at the moment. We are sorry for the inconvenience.",
					"Song not found (clip doesn't exist)",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public Song getCurrentSong()
	{
		return currentSong;
	}
	
	public Song[] getHitSongs()
	{
		return hitSongs;
	}
	public Song[] getClassicSongs()
	{
		return classicSongs;
	}
	
/////////////////////////////////////////////////////////
	
	/** This constant is not a "song". It is used to switch the jukebox to a different collection of songs.*/
	public static final Song JUKEBOX_SWITCH = new Song(" - SWITCH TO OTHER JUKEBOX", "");
	
	public static Icon setIcon(String urlString)
	{
		try
		{
			URL url = new URL(urlString);
			return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH));
		} catch (Exception e)
		{
			return null;
		}
	}
	
	public static int chooseJukebox(int type)
	{
		String[] options = new String[] {"2018's top hits", "Classic songs"};
		int newType = JOptionPane.showOptionDialog(
				null,
				"What type of music would you like to listen to?",
				"Jukebox Selection",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[type == 0 ? 1 : 0]);
		return newType;
	}
	
	public static boolean exitDialog()
	{
		int exit = JOptionPane.showConfirmDialog(
				null,
				"Would you like to exit the Ultimate Jukebox?",
				"Ultimate Jukebox",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (exit == JOptionPane.YES_OPTION || exit == JOptionPane.CLOSED_OPTION)
			return true;
		return false;
	}

/////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		UltimateJukebox jukebox = new UltimateJukebox();

		// SET MUSIC ICON (to display later; loading it sooner than later)
		Icon musicIcon = setIcon("https://cdn4.iconfinder.com/data/icons/public-sign-part03/100/_-14-256.png");

		// INTRO W/BACKGROUND MUSIC
		jukebox.setCurrentSong(new Song("Sandstorm (VORN Remix)", "Darude"));
		jukebox.play();

		JOptionPane.showMessageDialog(
				null,
				"Welcome to the Ultimate Jukebox!!\n\nHere you can find a wide range of music to play to your liking. Just choose which genre\nyou want to play, and then choose which song you'd like to listen to.\nIt's THAT easy!\n\nOur selection of music is a mixture of contemporary hits and memorable classics.\nWe hope you enjoy the jukebox!",
				"Ultimate Jukebox",
				JOptionPane.INFORMATION_MESSAGE,
				musicIcon);
		
		int type = chooseJukebox(1);
		if (type == JOptionPane.CLOSED_OPTION && exitDialog())
			return;
		
		// SONG SELECTION
		while (true)
		{
			Song songChoice = (Song) JOptionPane.showInputDialog(
					null,
					"What song would you like to listen to?",
					type == 0 ? "2018 Hit Songs" : "Classic Songs",
					JOptionPane.QUESTION_MESSAGE,
					null,
					type == 0 ? jukebox.getHitSongs() : jukebox.getClassicSongs(),
					(type == 0 ? jukebox.getHitSongs() : jukebox.getClassicSongs())[1]);

			if (songChoice == null)	// if Cancel or Close
			{
				if (exitDialog())
					return;
			}
			else if (songChoice == JUKEBOX_SWITCH)
			{
				type = chooseJukebox(type);
				if (type == JOptionPane.CLOSED_OPTION && exitDialog())
					return;
				continue;
			}
			else if (songChoice.isExplicit())
			{
				int explicit = JOptionPane.showConfirmDialog(
						null,
						"This song is explicit. Would you like to continue?",
						"Ultimate Jukebox",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (explicit == JOptionPane.NO_OPTION || explicit == JOptionPane.CLOSED_OPTION)
					continue;
			}

			jukebox.pause();
			jukebox.setCurrentSong(songChoice);
			
			if (!jukebox.clipExists())
				continue;
			
			jukebox.play();
			
			// SONG CONTROLS
			String[] options = {"PAUSE", "play", ">> 5s", "<< 5s", "[show time]", "STOP"};
			while (true)
			{
				int control;
				try
				{
					control = JOptionPane.showOptionDialog(
							null,
							"Currently playing: " + jukebox.getCurrentSong(),
							"Ultimate Jukebox",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							musicIcon,
							options, options[0]);
				} catch (Exception e)
				{
					control = JOptionPane.showOptionDialog(
							null,
							"Currently playing: " + jukebox.getCurrentSong(),
							"Ultimate Jukebox",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null,
							options, options[0]);
				}
				
				if (control == Arrays.asList(options).indexOf("PLAY"))
				{
					jukebox.play();
					options[0] = "PAUSE";
				}
				else if (control == Arrays.asList(options).indexOf("PAUSE") || control == Arrays.asList(options).indexOf("STOP") || control == JOptionPane.CLOSED_OPTION)
				{
					jukebox.pause();
					options[0] = "PLAY";
				}
				else if (control == Arrays.asList(options).indexOf("�?� 5s"))
				{
					jukebox.jump(-5);
				}
				else if (control == Arrays.asList(options).indexOf("�?� 5s"))
				{
					jukebox.jump(5);
				}
				else if (control == Arrays.asList(options).indexOf("�?�"))
				{
					jukebox.restart();
				}
				if (control == Arrays.asList(options).indexOf("STOP") || control == JOptionPane.CLOSED_OPTION)
					break;
				
				if (control == Arrays.asList(options).indexOf("[show time]"))
				{
					System.out.println(jukebox.getTime());
				}
			}
		}
	}
}