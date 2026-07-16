package com.learnhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learnhub.entity.QuizResult;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    List<QuizResult> findByStudent_Id(Long studentId);

    List<QuizResult> findByQuiz_Id(Long quizId);

    Optional<QuizResult> findByStudent_IdAndQuiz_Id(Long studentId,
                                                    Long quizId);

    @Query("SELECT AVG(qr.percentage) FROM QuizResult qr WHERE qr.quiz.id = :quizId")
    Double getAverageScore(@Param("quizId") Long quizId);

    @Query("SELECT COUNT(qr) FROM QuizResult qr WHERE qr.quiz.id = :quizId AND qr.percentage >= 50")
    Long countPassedStudents(@Param("quizId") Long quizId);

    @Query("SELECT COUNT(qr) FROM QuizResult qr WHERE qr.quiz.id = :quizId AND qr.percentage < 50")
    Long countFailedStudents(@Param("quizId") Long quizId);

}