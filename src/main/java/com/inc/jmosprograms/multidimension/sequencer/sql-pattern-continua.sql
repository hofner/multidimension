select id,r_continua,fecha,concurso from unidimension 
where 
@var1@=@var1@ and 
concurso>=@var2@-20 
and concurso<=@var2@
order by concurso ,id ;