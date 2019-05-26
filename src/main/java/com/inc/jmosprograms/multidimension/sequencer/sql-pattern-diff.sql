select fecha_int,diff,concurso from multidimension 
where
fecha>= str_to_date('1/7/2016','%d/%m/%Y') and 
fecha< str_to_date('1/1/2020','%d/%m/%Y') and
@var1@=@var1@ and 
concurso<=@var2@
order by fecha_int;