# Image Blurer
Place this file in the same folder as the image you want blurred, and name the image img.jpg.  
The output will be in blurred.jpg.  
Increase the value of <code>r</code> on line 27 to make it more blurry.

---
## Original Image:  
![unblurred image](https://user-images.githubusercontent.com/59327500/160023257-2ff57db6-28f8-4470-a613-2e9f090df2e6.JPG)  

## Blurred Image:  
![blurred image](https://user-images.githubusercontent.com/59327500/160025345-1fa00360-19ba-4606-a792-d41d05b4ff3d.jpg)

---
### Suggestions to Improve Speed
- add multithreading
- average color mustnt be completely re-calculated for each photo. Only the pixels that differ need to be re-calculated
