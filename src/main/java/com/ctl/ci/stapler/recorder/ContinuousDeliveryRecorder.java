/**
 * 
 */
package com.ctl.ci.stapler.recorder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.common.utils.DateUtils;
import com.ctl.ci.controller.ContinuousDeliveryController;
import com.ctl.ci.controller.STSServiceController;
import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.service.BuildUserService;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import jenkins.model.Jenkins;

/**
 * @author AB40286
 *
 */
public class ContinuousDeliveryRecorder extends Recorder {

	private final List<ContinuousDeliveryOptions> continuousDeliveryOptionsList;

	public List<ContinuousDeliveryOptions> getContinuousDeliveryOptionsList() {
		return continuousDeliveryOptionsList;
	}

	/**
	 * @param continuousDeliveryOptionsList
	 */
	@DataBoundConstructor
	public ContinuousDeliveryRecorder(final List<ContinuousDeliveryOptions> continuousDeliveryOptionsList) {
		super();
		this.continuousDeliveryOptionsList = continuousDeliveryOptionsList == null
				? new ArrayList<ContinuousDeliveryOptions>() : continuousDeliveryOptionsList;
	}

	@Override
	public DescriptorImpl getDescriptor() {
		return (DescriptorImpl) super.getDescriptor();
	}

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}

	/**
	 * @author AB40286
	 *
	 */
	@Extension
	public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

		@Override
		public String getDisplayName() {
			return "Continuous Delivery";
		}

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return true;
		}
	}

	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
			throws InterruptedException, IOException {
		List<Boolean> stsServiceExecutionList = null;
		final BuildUser buildUser = BuildUserService.getBuildUser(build);
		listener.getLogger().println("");
		listener.getLogger().println("");
		listener.hyperlink(Jenkins.getInstance().getRootUrl() + "/user/" + buildUser.getBuildUserId(),
				buildUser.getBuildUserFullName());
		listener.getLogger().println(" Has Initiated a Continuous Delivery.");
		listener.getLogger().println("");
		try {
			if (continuousDeliveryOptionsList != null && !continuousDeliveryOptionsList.isEmpty()) {
				stsServiceExecutionList = new ArrayList<Boolean>(continuousDeliveryOptionsList.size());
				for (Iterator<ContinuousDeliveryOptions> continuousDeliveryOptionsIterartor = continuousDeliveryOptionsList
						.iterator(); continuousDeliveryOptionsIterartor.hasNext();) {
					final ContinuousDeliveryOptions continuousDeliveryOptions = continuousDeliveryOptionsIterartor
							.next();
					listener.getLogger().println("");
					listener.getLogger().println(continuousDeliveryOptions);
					listener.getLogger().println("");
					stsServiceExecutionList.add(STSServiceController.callService(build, launcher, listener,
							continuousDeliveryOptions, buildUser));
				}
			}
		} catch (Throwable t) {
			listener.getLogger().println("Exception when executing the Continuous Delivery Step : " + t.getMessage());
			return false;
		}
		if (stsServiceExecutionList != null && !stsServiceExecutionList.isEmpty()) {
			return !stsServiceExecutionList.contains(Boolean.FALSE);
		}
		return false;
	}

	/**
	 * @author AB40286
	 *
	 */
	public final static class ContinuousDeliveryOptions extends Recorder {
		private final @Nonnull String applications;
		private final @Nonnull String environments;
		private final @Nonnull String projects;
		private final @Nonnull String dimensionDatabases;
		private final @Nonnull String dimensionProducts;
		private final @Nonnull String dimensionWorksets;
		private final @Nonnull String dimensionsVersion;
		private final @Nonnull String dimensionSMRs;
		private final @Nonnull String releaseMonth;
		private final @Nonnull String requestType;
		private final @Nonnull String mailingList;
		private final @Nonnull String codeFreezeStartDate;
		private final @Nonnull String codeFreezeEndDate;

		/**
		 * @param applications
		 * @param environments
		 * @param projects
		 * @param dimensionDatabases
		 * @param dimensionProducts
		 * @param dimensionWorksets
		 * @param dimensionSMRs
		 * @param dimensionsVersion
		 * @param releaseMonth
		 * @param requestType
		 * @param mailingList
		 */
		@DataBoundConstructor
		public ContinuousDeliveryOptions(@Nonnull final String applications, @Nonnull final String environments,
				@Nonnull final String projects, @Nonnull final String dimensionDatabases,
				@Nonnull final String dimensionProducts, @Nonnull final String dimensionWorksets,
				@Nonnull final String dimensionSMRs, final @Nonnull String dimensionsVersion,
				@Nonnull final String releaseMonth, @Nonnull final String requestType,
				@Nonnull final String mailingList, @Nonnull final String codeFreezeStartDate, @Nonnull final String codeFreezeEndDate) {
			this.applications = applications;
			this.environments = environments;
			this.projects = projects;
			this.dimensionDatabases = dimensionDatabases;
			this.dimensionProducts = dimensionProducts;
			this.dimensionWorksets = dimensionWorksets;
			this.dimensionSMRs = dimensionSMRs;
			this.releaseMonth = releaseMonth;
			this.requestType = requestType;
			this.dimensionsVersion = dimensionsVersion;
			this.mailingList = mailingList;
			this.codeFreezeStartDate = codeFreezeStartDate;
			this.codeFreezeEndDate = codeFreezeEndDate;
		}

		@Nonnull
		public String getApplications() {
			return applications;
		}

		@Nonnull
		public String getEnvironments() {
			return environments;
		}

		@Nonnull
		public String getProjects() {
			return projects;
		}

		@Nonnull
		public String getDimensionDatabases() {
			return dimensionDatabases;
		}

		@Nonnull
		public String getDimensionProducts() {
			return dimensionProducts;
		}

		@Nonnull
		public String getDimensionWorksets() {
			return dimensionWorksets;
		}

		@Nonnull
		public String getDimensionSMRs() {
			return dimensionSMRs;
		}

		public String getDimensionsVersion() {
			return dimensionsVersion;
		}

		@Nonnull
		public String getReleaseMonth() {
			return releaseMonth;
		}

		@Nonnull
		public String getRequestType() {
			return requestType;
		}

		@Nonnull
		public String getMailingList() {
			return mailingList;
		}

		
		
		/**
		 * @return the codeFreezeStartDate
		 */
		public String getCodeFreezeStartDate() {
			return codeFreezeStartDate;
		}

		

		/**
		 * @return the codeFreezeEndDate
		 */
		public String getCodeFreezeEndDate() {
			return codeFreezeEndDate;
		}

		@Override
		public String toString() {
			return "ContinuousDeliveryOptions [applications=" + applications + ", environments=" + environments
					+ ", projects=" + projects + ", dimensionDatabases=" + dimensionDatabases + ", dimensionProducts="
					+ dimensionProducts + ", dimensionWorksets=" + dimensionWorksets + ", dimensionsVersion="
					+ dimensionsVersion + ", dimensionSMRs=" + dimensionSMRs + ", releaseMonth=" + releaseMonth
					+ ", requestType=" + requestType + ", mailingList=" + mailingList + ", codeFreezeStartDate="
					+ codeFreezeStartDate + ", codeFreezeendDate=" + codeFreezeEndDate + "]";
		}


		/**
		 * @author AB40286
		 *
		 */
		@Extension
		public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

			@Override
			public String getDisplayName() {
				return "";
			}

			@Override
			public boolean isApplicable(Class<? extends AbstractProject> jobType) {
				return true;
			}

			public ListBoxModel doFillApplicationsItems() {
				try {
					return ContinuousDeliveryController.getApplicationsListBoxModel(DATA_BASE_TYPE.SQLSERVER);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				return new ListBoxModel();
			}

			public ListBoxModel doFillRequestTypeItems() {
				return ContinuousDeliveryController.getRequestTypeListBoxModel();
			}

			public ListBoxModel doFillEnvironmentsItems(@QueryParameter String applications) {
				try {
					return ContinuousDeliveryController.getEnvironmentListBoxModel(DATA_BASE_TYPE.SQLSERVER,
							applications);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				return new ListBoxModel();
			}

			public FormValidation doCheckApplications(@QueryParameter String applications)
					throws IOException, ServletException {
				if (StringUtils.isBlank(applications))
					return FormValidation.error("Please Select The Application");
				else if (StringUtils.contains(applications, "Please Select The Application"))
					return FormValidation.error("Please Select The Application");
				return FormValidation.ok();
			}

			public FormValidation doCheckEnvironments(@QueryParameter String environments)
					throws IOException, ServletException {
				if (StringUtils.isBlank(environments))
					return FormValidation.error("Please Select The Environment");
				else if (StringUtils.contains(environments, "Please Select The Environment"))
					return FormValidation.error("Please Select The Environment");
				return FormValidation.ok();
			}

			public FormValidation doCheckRequestType(@QueryParameter String requestType)
					throws IOException, ServletException {
				if (StringUtils.isBlank(requestType))
					return FormValidation.error("Please Select The RequestType");
				else if (StringUtils.contains(requestType, "Please Select The RequestType"))
					return FormValidation.error("Please Select The RequestType");
				return FormValidation.ok();
			}

			public FormValidation doCheckMailingList(@QueryParameter String mailingList)
					throws IOException, ServletException {
				if (StringUtils.isBlank(mailingList))
					return FormValidation.error("Please Enter The Mailing List");
				else if (StringUtils.contains(mailingList, "Please Enter The Mailing List"))
					return FormValidation.error("Please Enter The Mailing List");
				return FormValidation.ok();
			}

			public ListBoxModel doFillProjectsItems() {
				try {
					return ContinuousDeliveryController.getProjectListBoxModel(DATA_BASE_TYPE.SQLSERVER);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				return new ListBoxModel();
			}

			public ListBoxModel doFillReleaseMonthItems() {
				return ContinuousDeliveryController.getReleaseMonthListBoxModel();
			}

			public FormValidation doCheckReleaseMonth(@QueryParameter String releaseMonth)
					throws IOException, ServletException {
				if (StringUtils.isBlank(releaseMonth))
					return FormValidation.error("Please Select The Month");
				else if (StringUtils.contains(releaseMonth, "Please Select The Month"))
					return FormValidation.error("Please Select The Month");
				return FormValidation.ok();
			}

			public FormValidation doCheckProjects(@QueryParameter String projects)
					throws IOException, ServletException {
				if (StringUtils.isBlank(projects))
					return FormValidation.error("Please Select The Project");
				else if (StringUtils.contains(projects, "Please Select The Project"))
					return FormValidation.error("Please Select The Project");
				return FormValidation.ok();
			}

			public ListBoxModel doFillDimensionDatabasesItems() {
				return ContinuousDeliveryController.getDimensionsDatabaseListBoxModel();
			}

			public ListBoxModel doFillDimensionProductsItems(@QueryParameter String dimensionDatabases) {
				try {
					return ContinuousDeliveryController.getDimensionProductListBoxModel(dimensionDatabases);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				return new ListBoxModel();
			}

			public ListBoxModel doFillDimensionWorksetsItems(@QueryParameter String dimensionDatabases,
					@QueryParameter String dimensionProducts) {
				try {
					return ContinuousDeliveryController.getDimensionWorksetListBoxModel(dimensionDatabases,
							dimensionProducts);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				return new ListBoxModel();
			}

			public ListBoxModel doFillDimensionSMRsItems(@QueryParameter String dimensionDatabases,
					@QueryParameter String dimensionProducts, @QueryParameter String dimensionWorksets) {
				try {
					return ContinuousDeliveryController.getDimensionSMRListBoxModel(dimensionDatabases,
							new String[] { dimensionProducts, dimensionWorksets });
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				return new ListBoxModel();
			}

			public FormValidation doCheckDimensionDatabases(@QueryParameter String dimensionDatabases)
					throws IOException, ServletException {
				if (StringUtils.isBlank(dimensionDatabases))
					return FormValidation.error("Please Select The Dimensions Database");
				else if (StringUtils.contains(dimensionDatabases, "Please Select The Dimensions Database"))
					return FormValidation.error("Please Select The Dimensions Database");
				return FormValidation.ok();
			}

			public FormValidation doCheckDimensionProducts(@QueryParameter String dimensionProducts)
					throws IOException, ServletException {
				if (StringUtils.isBlank(dimensionProducts))
					return FormValidation.error("Please Select The Dimensions Product");
				else if (StringUtils.contains(dimensionProducts, "Please Select The Dimensions Product"))
					return FormValidation.error("Please Select The Dimensions Product");
				return FormValidation.ok();
			}

			public FormValidation doCheckDimensionWorksets(@QueryParameter String dimensionWorksets)
					throws IOException, ServletException {
				if (StringUtils.isBlank(dimensionWorksets))
					return FormValidation.error("Please Select The Dimensions Workset");
				else if (StringUtils.contains(dimensionWorksets, "Please Select Dimensions Workset"))
					return FormValidation.error("Please Select The Dimensions Workset");
				return FormValidation.ok();
			}

			public FormValidation doCheckDimensionSMRs(@QueryParameter String dimensionSMRs)
					throws IOException, ServletException {
				if (StringUtils.isBlank(dimensionSMRs))
					return FormValidation.error("Please Select The Dimensions SMR");
				else if (StringUtils.contains(dimensionSMRs, "Please Select Dimensions SMR"))
					return FormValidation.error("Please Select The Dimensions SMR");
				return FormValidation.ok();
			}

			public FormValidation doCheckDimensionsVersion(@QueryParameter String dimensionsVersion)
					throws IOException, ServletException {
				if (StringUtils.isBlank(dimensionsVersion))
					return FormValidation.error("Please Enter The Dimensions Version");
				else if (StringUtils.contains(dimensionsVersion, "Please Enter The Dimensions Version"))
					return FormValidation.error("Please Enter The Dimensions Version");
				return FormValidation.ok();
			}
			
			public FormValidation doCheckCodeFreezeStartDate(@QueryParameter String codeFreezeStartDate)
					throws IOException, ServletException {
				if (StringUtils.isBlank(codeFreezeStartDate))
					return FormValidation.error("Start Date Can't Be Empty. Please Enter The Valid Start Date In DD/MM/YYYY. EX: "+DateUtils.getTodaysDate());
				else if (StringUtils.contains(codeFreezeStartDate, "DD/MM/YYYY"))
					return FormValidation.error("Please Enter The Valid Start Date in DD/MM/YYYY. EX: "+DateUtils.getTodaysDate());
				else if (!DateUtils.isValidDate(codeFreezeStartDate))
					return FormValidation.error(codeFreezeStartDate+" Is Not A Validate Date. Please Enter The Valid Start Date In DD/MM/YYYY. EX: "+DateUtils.getTodaysDate());
				return FormValidation.ok();
			}
			
			public FormValidation doCheckCodeFreezeEndDate(@QueryParameter String codeFreezeEndDate)
					throws IOException, ServletException {
				if (StringUtils.isBlank(codeFreezeEndDate))
					return FormValidation.error("End Date Can't Be Empty. Please Enter The Valid End Date In DD/MM/YYYY. EX: "+DateUtils.getTodaysDate());
				else if (StringUtils.contains(codeFreezeEndDate, "DD/MM/YYYY"))
					return FormValidation.error("Please Enter The Valid End Date in DD/MM/YYYY. EX: "+DateUtils.getTodaysDate());
				else if (!DateUtils.isValidDate(codeFreezeEndDate))
					return FormValidation.error(codeFreezeEndDate+" Is Not A Validate Date. Please Enter The Valid End Date In DD/MM/YYYY. EX: "+DateUtils.getTodaysDate());
				return FormValidation.ok();
			}
			
		}

		@Override
		public DescriptorImpl getDescriptor() {
			return (DescriptorImpl) super.getDescriptor();
		}

		public BuildStepMonitor getRequiredMonitorService() {
			return BuildStepMonitor.NONE;
		}
	}

}
