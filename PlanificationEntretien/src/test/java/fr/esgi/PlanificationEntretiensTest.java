package fr.esgi;

import fr.esgi.exceptions.BusyConsultantRecruteur;
import fr.esgi.exceptions.UnmatchingCompetenceConsultantRecruteur;
import fr.esgi.interfaces.PlanificationEntretiens;
import fr.esgi.interfaces.business.PlanificationEntretienBusiness;
import fr.esgi.models.Candidat;
import fr.esgi.models.Compétence;
import fr.esgi.models.CreneauHoraire;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanificationEntretiensTest extends TestCase {
    PlanificationEntretiens planificationEntretiens = Mockito.mock(PlanificationEntretienBusiness.class);

    public void test_should_planifier_entretien()
    {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.AGILITE);
        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();

        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenReturn(true);


        Assert.assertTrue(planificationEntretiens.planifierEntretien(candidat,creneauHoraire));
    }

    public void test_should_not_planifier_entretien_without_ConsultantRecruteur_with_competences()
    {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.AGILITE);
        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();

        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new UnmatchingCompetenceConsultantRecruteur());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("Aucun consultant recruteur ne posséde la compétence requise.", e.getMessage());
        }
    }

    public void test_should_not_planifier_entretien_when_ConsultantRecruteur_is_busy()
     {
         List<Compétence> compétences = new ArrayList<>();
         compétences.add(Compétence.AGILITE);
         Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
         CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();

         Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new BusyConsultantRecruteur());

         try {
             planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
         } catch(Exception e){
             Assert.assertEquals("Aucun consultant recruteur n'est disponible.", e.getMessage());
         }
     }
}
