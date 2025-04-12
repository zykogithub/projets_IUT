using UnityEngine;
using UnityEngine.SceneManagement;

public class HomeManager : MonoBehaviour
{
    private int score;
    private int niveau;
    private string etatJeu;
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void DemarrerJeu()
    {
        score = 0;
        SceneManager.LoadScene("Game",LoadSceneMode.Single);
    }
    public void TerminerJeu()
    {
        SceneManager.LoadScene("Lobby",LoadSceneMode.Single);
    }
    public void MettreAJourEtatJeu()
    {
        SceneManager.LoadScene("Options", LoadSceneMode.Single);

    }
}


