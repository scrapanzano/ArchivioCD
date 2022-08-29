package it.unibs.fp.archiviocd;

public class Brano {

	private static final String DESCRIZIONE_BRANO = "Titolo: %s, durata: (%d:%02d)%n";
	private static final int SECONDI_AL_MINUTO = 60;
	
	private String titolo;
	private int minuti;
	private int secondi;
	private int durataTotaleSecondi;
	
	/**
	 * <p>
	 * Creazione di un brano tramite:
	 * <ul>
	 * <li>Titolo del brano;</li>
	 * <li>Durata totale in secondi del brano.</li>
	 * </ul> 
	 * Vengono effettuate due operazioni:
	 * <ol>
	 * <li>Vengono estratti i minuti dalla durata totale in secondi;</li>
	 * <li>Vengono estratti i secondi dalla durata totale in secondi.</li>
	 * </ol>
	 * </p>
	 * @param _titolo
	 * @param _durataTotaleSecondi
	 */
	public Brano(String _titolo, int _durataTotaleSecondi) {
		this.titolo = _titolo;
		this.durataTotaleSecondi = _durataTotaleSecondi;
		this.minuti = this.durataTotaleSecondi / SECONDI_AL_MINUTO; //Vengono estratti i minuti dalla durata totale in secondi.
		this.secondi = this.durataTotaleSecondi %  SECONDI_AL_MINUTO; //Vengono estratti i secondi dalla durata totale in secondi.
	}
	
	/**
	 * <p>
	 * Creazione di un brano tramite:
	 * <ul>
	 * <li>Titolo del brano;</li>
	 * <li>Minuti del brano;</li>
	 * <li>Secondi del brano.</li>
	 * </ul>
	 * </p>
	 * @param _titolo
	 * @param _minuti
	 * @param _secondi
	 */
	public Brano(String _titolo, int _minuti, int _secondi) {
		this.titolo = _titolo;
		this.minuti = _minuti;
		this.secondi = _secondi;
		this.durataTotaleSecondi = this.minuti * SECONDI_AL_MINUTO + _secondi;
	}
	
	/**
	 * <p>Confronta il titolo cercato dall'utente con quello del brano.</p>
	 * @param titoloCercato
	 * @return True se l'argomento non e' null e rappresenta una stringa equivalente ignorando le maiuscole, False altrimenti.
	 * 
	 */
	public boolean confrontaTitoloBrano(String titoloCercato) {
		titoloCercato.trim();
		return this.titolo.equalsIgnoreCase(titoloCercato);
	}
	
	/**
	 * <p>Fornisce una descrizione del brano mostrando: 
	 * <ul>
	 * <li>Titolo del brano;</li>
	 * <li>Minuti del brano;</li>
	 * <li>Secondi del brano.</li>
	 * </ul>
	 * </p>
	 * @return Descrizione tramite stringa dell'oggetto Brano.
	 */
	@Override
	public String toString() {
		return String.format(DESCRIZIONE_BRANO, this.titolo, this.minuti, this.secondi);
	}
}
