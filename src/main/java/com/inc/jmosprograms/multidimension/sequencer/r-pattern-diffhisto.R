my.data<-read.csv("R@var1@/R@var1@-forDiffhisto-@fecha@-@var2@.csv")
ggplot(my.data, aes(x = diff)) +
  geom_histogram(binwidth = 1, colour = "blue", fill = "white")+
  scale_x_continuous(limits = c(0, 56), breaks = 1:56)+
  geom_vline(aes(xintercept = mean(diff, na.rm = T)),
             colour = "red", linetype ="longdash", size = 1)+
  geom_vline(aes(xintercept = median(diff, na.rm = T)),
             colour = "orange", linetype ="longdash", size = 1)+
  geom_vline(aes(xintercept = sd(diff, na.rm = T)),
             colour = "green", linetype ="longdash", size = 1)+
  geom_vline(aes(xintercept = var(diff, na.rm = T)),
             colour = "yellow", linetype ="longdash", size = 1)+
  geom_vline(aes(xintercept = my.data[1,1]),
             colour = "blue", linetype ="longdash", size = 1)
ggsave("R@var1@/R@var1@-forDiffhisto-@fecha@-@var2@.jpg", width=14.80, height=8.03, dpi = 100,device="jpeg" )
mensaje<-paste("imagen generada " ,"R@var1@/R@var1@-forDiffhisto-@fecha@-@var2@.jpg")
mensaje            