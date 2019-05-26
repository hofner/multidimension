my.data<-read.csv("R@var1@/R@var1@-forDiff-@fecha@-@var2@.csv")
ggplot(my.data,aes(x=concurso,y=diff)) +
  geom_line()+
  geom_point()+
  scale_y_continuous(limits = c(0, 56), breaks = 1:56)+
  geom_smooth(method = "loess")+
  geom_smooth(method = lm,colour = "orange")+
  geom_hline(aes(yintercept = mean(diff, na.rm = T)),
             colour = "red", linetype ="longdash", size = 1)+
  geom_hline(aes(yintercept = median(diff, na.rm = T)),
             colour = "orange", linetype ="longdash", size = 1)+
  geom_hline(aes(yintercept = sd(diff, na.rm = T)),
             colour = "green", linetype ="longdash", size = 1)+
  geom_hline(aes(yintercept = var(diff, na.rm = T)),
             colour = "yellow", linetype ="longdash", size = 1)
ggsave("R@var1@/R@var1@-forDiff-@fecha@-@var2@.jpg", width=14.80, height=8.03, dpi = 100,device="jpeg" )