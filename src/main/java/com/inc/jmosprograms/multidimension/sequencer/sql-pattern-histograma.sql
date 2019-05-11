select R@var1@,CONCURSO,fecha from multidimension 
where 
fecha>= str_to_date('1/1/2009','%d/%m/%Y') and 
fecha< str_to_date('1/1/2020','%d/%m/%Y') 
and concurso<=@var2@
order by CONCURSO desc;