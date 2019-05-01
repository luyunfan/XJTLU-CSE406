CREATE PROCEDURE `p_compute`(IN hometown VARCHAR(45),
                             OUT amount INT)
BEGIN
  SELECT COUNT(*) INTO amount
  FROM t_student
  WHERE student_country = hometown;
END;

