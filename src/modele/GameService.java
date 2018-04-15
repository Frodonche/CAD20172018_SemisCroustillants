package modele;

public interface GameService{
	
	public void serialize(Game g);
	public GameDAO deserialize(String s);

}