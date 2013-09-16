package org.eclipse.jface.viewer;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CheckBoxCellEditorMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		
		final TableViewer v = new TableViewer(parent, SWT.FULL_SELECTION);
		TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);
		
	
		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);
				
		final TableViewerColumn vColumn1 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column1 = vColumn1.getColumn();
		layout.setColumnData(column1, new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, false));
		column1.setText("Name");
		
		vColumn1.setLabelProvider(new ColumnLabelProvider(){
			
              public String getText(Object element) {
				PersonModel m = (PersonModel) element;
				return m.name;
				
				}
		});
		
		
		final TableViewerColumn vColumn2 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column2 = vColumn2.getColumn();
		layout.setColumnData(column2, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, false));
		column2.setText("Married?");
		
		
		
		vColumn2.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(v));
		
		
		
		final TableViewerColumn vColumn3 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column3 = vColumn3.getColumn();
		layout.setColumnData(column3, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, false));
		column3.setText("Check Item");
		
		        
        vColumn3.setLabelProvider(new ColumnLabelProvider(){
			
            public String getText(Object element) {
				PersonModel m = (PersonModel) element;
				return Boolean.toString(m.isMarried);
				
            	
				}
           
            
          
            
		});
		
				
		
		vColumn2.setEditingSupport(new EditingSupport(v) {
			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
			
			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(v.getTable(), SWT.CHECK | SWT.READ_ONLY);
			}
			
			@Override
			protected Object getValue(Object element) {
				PersonModel person = (PersonModel) element;
				return person.isMarried;
			}
			
			@Override
			protected void setValue(Object element, Object value) {
				
				((PersonModel)element).isMarried = (Boolean)value; 
				v.refresh();
			}
		});
		
		
		v.setContentProvider(new MyContentProvider());
		PersonModel[] m = createModel();
		v.setInput(m);
		
		return null;
	}

        private class MyContentProvider implements IStructuredContentProvider {

		
		public Object[] getElements(Object inputElement) {
			return (PersonModel[])inputElement;
		}

		public void dispose() {
			
		}
		
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			
		}
		
	}
	
        
    ////////////////////
        
        public class EmulatedNativeCheckBoxLabelProvider extends
        ColumnLabelProvider {
      private static final String CHECKED_KEY = "CHECKED";
      private static final String UNCHECK_KEY = "UNCHECKED";

      public EmulatedNativeCheckBoxLabelProvider(ColumnViewer viewer) {
        if (JFaceResources.getImageRegistry().getDescriptor(CHECKED_KEY) == null) {
          JFaceResources.getImageRegistry().put(UNCHECK_KEY,
              makeShot(viewer.getControl(), false));
          JFaceResources.getImageRegistry().put(CHECKED_KEY,
              makeShot(viewer.getControl(), true));
        }
      }

      private Image makeShot(Control control, boolean type)
      {
        // Hopefully no platform uses exactly this color because we'll make
        // it transparent in the image.
        Color greenScreen = new Color(control.getDisplay(), 222, 223, 224);

        Shell shell = new Shell(control.getShell(), SWT.NO_TRIM);

        // otherwise we have a default gray color
        shell.setBackground(greenScreen);

        Button button = new Button(shell, SWT.CHECK);
        button.setBackground(greenScreen);
        button.setSelection(type);

        // otherwise an image is located in a corner
        button.setLocation(1, 1);
        Point bsize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT);

        // otherwise an image is stretched by width
        bsize.x = Math.max(bsize.x - 1, bsize.y - 1);
        bsize.y = Math.max(bsize.x - 1, bsize.y - 1);
        button.setSize(bsize);
        shell.setSize(bsize);

        shell.open();
        GC gc = new GC(shell);
        Image image = new Image(control.getDisplay(), bsize.x, bsize.y);
        gc.copyArea(image, 0, 0);
        gc.dispose();
        shell.close();

        ImageData imageData = image.getImageData();
        imageData.transparentPixel = imageData.palette.getPixel(greenScreen
            .getRGB());

        return new Image(control.getDisplay(), imageData);
      }

      public Image getImage(Object element) {
        if (((PersonModel)element).isMarried) {
          return JFaceResources.getImageRegistry().get(CHECKED_KEY);
        } else {
          return JFaceResources.getImageRegistry().get(UNCHECK_KEY);
        }
      }

      
      public String getText(Object element) {
          if (((PersonModel)element).isMarried) {
            return "Married";
          } else {
            return "Not Married";
          }
        }
      
      
      
      //protected boolean isChecked(Object element);
    }
        
    ////////////////////////    
        
	public class PersonModel{
		
		String name;
		boolean isMarried;
		
		PersonModel(String n, boolean f){
			
			this.name = n;
			this.isMarried = f;
			
		}
		
		public void setMarried(Boolean f){
		
		this.isMarried = f;
	}
	}
	public PersonModel[] createModel(){
		
		PersonModel[] elements = new PersonModel[5];
		String[] names = new String[]{"Ann", "Peter", "John", "Mary", "Kate"};
		int i;				
		for(i = 0; i<2; i++){
			elements[i] = new PersonModel(names[i], true);
		}
		for(i = 2; i<5; i++){
			elements[i] = new PersonModel(names[i], false);
		}
		
		return elements;
	}
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "CheckBox Cell Editor Test";
	}

}
